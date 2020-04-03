package pl.anitakowalczyk.surwejlansapi.dao.repository;

import org.springframework.data.repository.CrudRepository;
import pl.anitakowalczyk.surwejlansapi.dao.entity.Subscription;

public interface SubscriptionRepository extends CrudRepository<Subscription, Integer> {

}
