package pl.anitakowalczyk.surwejlansapi.service.api;

import pl.anitakowalczyk.surwejlansapi.dao.entity.Subscription;
import pl.anitakowalczyk.surwejlansapi.errorhandling.exception.AllegroApiFatalException;
import pl.anitakowalczyk.surwejlansapi.errorhandling.exception.IllegalAccessException;
import pl.anitakowalczyk.surwejlansapi.errorhandling.exception.InvalidInputException;
import pl.anitakowalczyk.surwejlansapi.errorhandling.exception.NotFoundException;

import java.io.IOException;
import java.util.Map;

public interface SubscriptionsService {

    Iterable<Subscription> findAll();

    Subscription save(Subscription subscription);

    Map<Integer, String> getSubscriptionsList() throws NotFoundException;

    Subscription findSubscriptionById(Integer subscriptionId) throws IllegalAccessException, NotFoundException;

    Subscription addNewSubscription(Subscription subscription)
            throws InvalidInputException;

    Subscription updateSubscription(Subscription subscription) throws IllegalAccessException, InvalidInputException, NotFoundException;
}
