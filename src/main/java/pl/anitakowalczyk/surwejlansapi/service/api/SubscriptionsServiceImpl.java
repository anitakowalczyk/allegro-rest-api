package pl.anitakowalczyk.surwejlansapi.service.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.anitakowalczyk.surwejlansapi.dao.entity.Subscription;
import pl.anitakowalczyk.surwejlansapi.dao.repository.SubscriptionRepository;
import pl.anitakowalczyk.surwejlansapi.errorhandling.exception.IllegalAccessException;
import pl.anitakowalczyk.surwejlansapi.errorhandling.exception.InvalidInputException;
import pl.anitakowalczyk.surwejlansapi.errorhandling.exception.NotFoundException;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class SubscriptionsServiceImpl implements SubscriptionsService {

    private final SubscriptionRepository subscriptionRepository;
    private final AuthenticationService authenticationService;

    @Autowired
    public SubscriptionsServiceImpl(SubscriptionRepository subscriptionRepository, AuthenticationService authenticationService) {
        this.subscriptionRepository = subscriptionRepository;
        this.authenticationService = authenticationService;
    }

    public Iterable<Subscription> findAll() {
        return subscriptionRepository.findAll();
    }

    public Subscription save(Subscription subscription) {
        return subscriptionRepository.save(subscription);
    }

    public Map<Integer, String> getSubscriptionsList() {
        Integer currentUserId = authenticationService.getCurrentUser().getId();
        Map<Integer, String> userSubscriptions = StreamSupport.stream(findAll().spliterator(), false)
                .filter(s -> s.getUser().getId().equals(currentUserId))
                .collect(Collectors.toMap(s -> s.getId(), s -> s.getName()));
        return userSubscriptions;
    }

    private Subscription validateAccessToSubscription(Integer subscriptionId) throws NotFoundException, IllegalAccessException {
        if (subscriptionId == null) {
            throw new NotFoundException("Subscription id is required!");
        }
        Optional<Subscription> subscription = subscriptionRepository.findById(subscriptionId);
        if (!subscription.isPresent()) {
            throw new NotFoundException("There is no subscription with the given id");
        }
        if (subscription.get().getUser().getId() != authenticationService.getCurrentUser().getId()) {
            throw new IllegalAccessException("Subscription with the given id doesn't belong to the user with the given id");
        }
        return subscription.get();
    }
  
    public Subscription findSubscriptionById(Integer subscriptionId) throws NotFoundException, IllegalAccessException {
        return validateAccessToSubscription(subscriptionId);
    }

    private boolean isAtLeastOneRequried(Subscription subscription) throws InvalidInputException {
        if ((subscription.getCategoryId() != null && !subscription.getCategoryId().trim().isEmpty())
                || (subscription.getPhrase() != null && !subscription.getPhrase().trim().isEmpty())
                || (subscription.getSellerId() != null && !subscription.getSellerId().trim().isEmpty())) {
            return true;
        } else {
            throw new InvalidInputException("At least one of parameters: category, phrase, seller is required!");
        }
    }

    private boolean isNameRequired(Subscription subscription) throws InvalidInputException {
        if ((subscription.getName() != null && !subscription.getName().trim().isEmpty())) {
            return true;
        } else {
            throw new InvalidInputException("Name is required!");
        }
    }

    private boolean isValidSubscription(Subscription subscription) throws InvalidInputException {
        return isNameRequired(subscription) && isAtLeastOneRequried(subscription);
    }

    public Subscription addNewSubscription(Subscription subscriptionData) throws InvalidInputException {
        Timestamp now = new Timestamp(new Date().getTime());
        Subscription subscription = new Subscription(subscriptionData.getName(), authenticationService.getCurrentUser(), subscriptionData.getCategoryId(), subscriptionData.getPhrase(), subscriptionData.getSellerId(), now, true);
        if (isValidSubscription(subscription)) {
            return save(subscription);
        } else {
            throw new InvalidInputException("Unhandled validation error");
        }
    }

    public Subscription updateSubscription(Subscription subscription)
            throws InvalidInputException, NotFoundException, IllegalAccessException {
        Subscription updatedSubscription = validateAccessToSubscription(subscription.getId());
        updatedSubscription.setName(subscription.getName());
        updatedSubscription.setCategoryId(subscription.getCategoryId());
        updatedSubscription.setPhrase(subscription.getPhrase());
        updatedSubscription.setSellerId(subscription.getSellerId());
        updatedSubscription.setActive(subscription.isActive());
        if (isValidSubscription(updatedSubscription)) {
            return subscriptionRepository.save(updatedSubscription);
        } else {
            throw new InvalidInputException("Unhandled validation error");
        }
    }
}