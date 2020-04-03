package pl.anitakowalczyk.surwejlansapi.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.anitakowalczyk.surwejlansapi.dao.entity.Subscription;
import pl.anitakowalczyk.surwejlansapi.dao.entity.User;
import pl.anitakowalczyk.surwejlansapi.errorhandling.exception.AllegroApiFatalException;
import pl.anitakowalczyk.surwejlansapi.errorhandling.exception.IllegalAccessException;
import pl.anitakowalczyk.surwejlansapi.errorhandling.exception.InvalidInputException;
import pl.anitakowalczyk.surwejlansapi.errorhandling.exception.NotFoundException;
import pl.anitakowalczyk.surwejlansapi.service.api.SubscriptionsService;
import pl.anitakowalczyk.surwejlansapi.service.api.UserService;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/rest/allegro")
public class SubscriptionsRestController {

    private SubscriptionsService subscriptionsRestService;
    private UserService userService;

    @Autowired
    public SubscriptionsRestController(SubscriptionsService subscriptionsRestService, UserService userService) {
        this.subscriptionsRestService = subscriptionsRestService;
        this.userService = userService;
    }

    @GetMapping("/users")
    public Iterable<User> getAllUsers() {
        return userService.findAll();
    }

    @GetMapping("/subscriptions")
    public Map<Integer, String> getSubscriptionsList() throws NotFoundException {
        return subscriptionsRestService.getSubscriptionsList();
    }

    @GetMapping("/subscriptions/{subscriptionId}")
    public Subscription getSubscriptionById(@PathVariable Integer subscriptionId) throws IllegalAccessException, NotFoundException {
        return subscriptionsRestService.findSubscriptionById(subscriptionId);
    }

    @PostMapping("/subscriptions")

    public Subscription addSubscription(@RequestBody Subscription subscription) throws InvalidInputException {
        return subscriptionsRestService.addNewSubscription(subscription);
    }

    @PutMapping("/subscriptions/{subscriptionId}")
    public Subscription updateSubscription(@PathVariable Integer subscriptionId, @RequestBody Subscription subscription)
            throws IllegalAccessException, InvalidInputException, NotFoundException {
        subscription.setId(subscriptionId);
        return subscriptionsRestService.updateSubscription(subscription);
    }
}
