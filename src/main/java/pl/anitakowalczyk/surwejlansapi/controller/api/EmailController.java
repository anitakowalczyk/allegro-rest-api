package pl.anitakowalczyk.surwejlansapi.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.anitakowalczyk.surwejlansapi.service.email.Email;
import pl.anitakowalczyk.surwejlansapi.service.email.EmailService;
import pl.anitakowalczyk.surwejlansapi.service.email.SubscriptionEmailService;

import javax.mail.MessagingException;

@Controller
@RequestMapping("/email")
public class EmailController {

    private EmailService emailService;
    private SubscriptionEmailService subscriptionEmailService;

    @Autowired
    public EmailController(EmailService emailService, SubscriptionEmailService subscriptionEmailService) {
        this.emailService = emailService;
        this.subscriptionEmailService = subscriptionEmailService;
    }

    @GetMapping("/send")
    public ModelAndView viewEmailForm() {
        return new ModelAndView("emailForm", "email", new Email());
    }

    @PostMapping("/sent")
    public ModelAndView sendSubscriptionEmail(@ModelAttribute("email") Email email, Model model) {
        try {
            emailService.sendSubscriptionEmail(email);
            return new ModelAndView("emailConfirmation");
        } catch (MessagingException e) {
            model.addAttribute("error", e.getMessage());
            return new ModelAndView("error");
        }
    }

    @GetMapping("/offers")
    public ModelAndView sendSubscriptionEmail(@RequestParam(required = false, value = "subscriptionId") Integer subscriptionId, Model model) {
        try {
            subscriptionEmailService.send(subscriptionId);
            return new ModelAndView("emailConfirmation");
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return new ModelAndView("error");
        }
    }
}
