package pl.anitakowalczyk.surwejlansapi.service.api;

import org.springframework.beans.factory.annotation.Autowired;
import pl.anitakowalczyk.surwejlansapi.dao.entity.User;
import pl.anitakowalczyk.surwejlansapi.dao.repository.UserRepository;

public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;

    @Autowired
    public AuthenticationServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getCurrentUser() {
        return userRepository.findById(1).get();
    }
}
