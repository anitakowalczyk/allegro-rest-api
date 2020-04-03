package pl.anitakowalczyk.surwejlansapi.service.email;

import pl.anitakowalczyk.surwejlansapi.errorhandling.exception.AllegroApiFatalException;
import pl.anitakowalczyk.surwejlansapi.errorhandling.exception.ConnectException;
import pl.anitakowalczyk.surwejlansapi.errorhandling.exception.IllegalAccessException;
import pl.anitakowalczyk.surwejlansapi.errorhandling.exception.NotFoundException;

import javax.mail.MessagingException;
import java.io.IOException;


public interface SubscriptionEmailService {

    void send(Integer subscriptionId) throws NotFoundException, IllegalAccessException, AllegroApiFatalException, IOException, MessagingException, ConnectException;
}
