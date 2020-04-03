package pl.anitakowalczyk.surwejlansapi.service.api;

import pl.anitakowalczyk.surwejlansapi.dao.entity.User;

public interface AuthenticationService {

    public User getCurrentUser();
}
