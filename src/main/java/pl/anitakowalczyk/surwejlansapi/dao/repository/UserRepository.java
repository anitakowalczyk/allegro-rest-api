package pl.anitakowalczyk.surwejlansapi.dao.repository;

import org.springframework.data.repository.CrudRepository;
import pl.anitakowalczyk.surwejlansapi.dao.entity.User;

public interface UserRepository extends CrudRepository<User, Integer> {

}
