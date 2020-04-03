package pl.anitakowalczyk.surwejlansapi.service.api;

import pl.anitakowalczyk.surwejlansapi.dao.entity.User;

import java.util.Optional;

public interface UserService {

    Iterable<User> findAll();

    Optional<User> findById(Integer userId);

    User save(User user);

}
