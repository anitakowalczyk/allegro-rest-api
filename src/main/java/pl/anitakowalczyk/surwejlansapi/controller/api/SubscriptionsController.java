package pl.anitakowalczyk.surwejlansapi.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.anitakowalczyk.surwejlansapi.dao.entity.Subscription;
import pl.anitakowalczyk.surwejlansapi.errorhandling.exception.IllegalAccessException;
import pl.anitakowalczyk.surwejlansapi.errorhandling.exception.InvalidInputException;
import pl.anitakowalczyk.surwejlansapi.errorhandling.exception.NotFoundException;
import pl.anitakowalczyk.surwejlansapi.service.api.AuthenticationService;
import pl.anitakowalczyk.surwejlansapi.service.api.SubscriptionsService;

import java.util.Map;

@Controller
@RequestMapping("/subscriptions")
public class SubscriptionsController {

    private SubscriptionsService subscriptionsRestService;
    private AuthenticationService authenticationService;

    @Autowired
    public SubscriptionsController(SubscriptionsService subscriptionsRestService, AuthenticationService authenticationService) {
        this.subscriptionsRestService = subscriptionsRestService;
        this.authenticationService = authenticationService;
    }

    @GetMapping("/list")
    public String getUserSubscriptions(Model model) {
        try {
            Map<Integer, String> subscriptions = subscriptionsRestService.getSubscriptionsList();
            model.addAttribute("userId", authenticationService.getCurrentUser().getId());
            model.addAttribute("subscriptions", subscriptions);
            return "subscriptions";
        } catch (NotFoundException e) {
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }

    @GetMapping("/subscription")
    public String getSubscriptionById(Model model, @RequestParam(required = false, value = "id") Integer subscriptionId) {
        try {
            Subscription subscription = subscriptionsRestService.findSubscriptionById(subscriptionId);
            model.addAttribute("subscription", subscription);
            return "subscription";
        } catch (NotFoundException | IllegalAccessException e) {
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }

    private RedirectAttributes addOldSubscription(RedirectAttributes attributes, Subscription subscription) {
        attributes.addFlashAttribute("oldSubscription", subscription);
        attributes.addAttribute("name", subscription.getName());
        attributes.addAttribute("categoryId", subscription.getCategoryId());
        attributes.addAttribute("phrase", subscription.getPhrase());
        attributes.addAttribute("sellerId", subscription.getSellerId());
        attributes.addAttribute("active", subscription.isActive());
        return attributes;
    }

    @GetMapping("/add")
    public ModelAndView viewAddSubscriptionForm(@ModelAttribute("error") String error,
                                                @ModelAttribute("oldSubscription") Subscription oldSubscription) {
        ModelAndView modelAndView = new ModelAndView("subscriptionAdding", "subscription", new Subscription());
        modelAndView.addObject("oldSubscription", oldSubscription);
        if (error != null && !error.trim().isEmpty()) {
            modelAndView.addObject("error", error);
        }
        return modelAndView;
    }

    @PostMapping("/added")
    public ModelAndView addSubscription(@ModelAttribute("subscription") Subscription subscription,
                                        BindingResult result, Model model, RedirectAttributes attributes) {
        try {
            subscriptionsRestService.addNewSubscription(subscription);
            return new ModelAndView("redirect:/subscriptions/list");
        } catch (InvalidInputException e) {
            attributes = addOldSubscription(attributes, subscription);
            attributes.addFlashAttribute("error", e.getMessage());
            return new ModelAndView("redirect:/subscriptions/add");
        }
    }

    @GetMapping("/update")
    public ModelAndView updateSubscriptionForm(@RequestParam(required = false, value = "id") Integer subscriptionId, Model model) {
        try {
            Subscription oldSubscription = subscriptionsRestService.findSubscriptionById(subscriptionId);
            ModelAndView modelAndView = new ModelAndView("subscriptionUpdating", "subscription", new Subscription());
            modelAndView.addObject("oldSubscription", oldSubscription);
            return modelAndView;
        } catch (NotFoundException | IllegalAccessException e) {
            model.addAttribute("error", e.getMessage());
            return new ModelAndView("error");
        }
    }

    @PostMapping("/updated")
    public ModelAndView updateSubscription(@ModelAttribute("subscription") Subscription subscription,
                                           BindingResult result, Model model, RedirectAttributes attributes) {
        try {
            subscriptionsRestService.updateSubscription(subscription);
            return new ModelAndView("redirect:/subscriptions/subscription?id=" + subscription.getId());
        } catch (InvalidInputException e) {
            attributes.addFlashAttribute("error", e.getMessage());
            return new ModelAndView("redirect:/subscriptions/update?id=" + subscription.getId());
        } catch (NotFoundException | IllegalAccessException e) {
            model.addAttribute("error", e.getMessage());
            return new ModelAndView("error");
        }
    }
}