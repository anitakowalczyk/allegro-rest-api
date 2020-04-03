package pl.anitakowalczyk.surwejlansapi.service.email;

import javax.mail.MessagingException;

public interface EmailService {

    void sendSubscriptionEmail(Email email) throws MessagingException;
}
