package pl.anitakowalczyk.surwejlansapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.client.RestTemplate;
import pl.anitakowalczyk.surwejlansapi.controller.api.AllegroController;
import pl.anitakowalczyk.surwejlansapi.controller.api.EmailController;
import pl.anitakowalczyk.surwejlansapi.controller.api.SubscriptionsRestController;
import pl.anitakowalczyk.surwejlansapi.dao.repository.OfferRepository;
import pl.anitakowalczyk.surwejlansapi.dao.repository.SubscriptionRepository;
import pl.anitakowalczyk.surwejlansapi.dao.repository.UserRepository;
import pl.anitakowalczyk.surwejlansapi.service.api.*;
import pl.anitakowalczyk.surwejlansapi.service.database.OfferService;
import pl.anitakowalczyk.surwejlansapi.service.database.OfferServiceImpl;
import pl.anitakowalczyk.surwejlansapi.service.email.*;

@SpringBootApplication
public class SurwejlansApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(SurwejlansApiApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    AllegroService allegroService(RestTemplate restTemplate) {
        return new AllegroServiceImpl(restTemplate);
    }

    @Bean
    AllegroOfferService allegroOfferService(RestTemplate restTemplate, AllegroService allegroService) {
        return new AllegroOfferServiceImpl(restTemplate, allegroService);
    }

    @Bean
    AllegroCategoryService allegroCategoryService(RestTemplate restTemplate, AllegroService allegroService) {
        return new AllegroCategoryServiceImpl(restTemplate, allegroService);
    }

    @Bean
    AllegroFilterService allegroFilterService(RestTemplate restTemplate, AllegroService allegroService) {
        return new AllegroFilterServiceImpl(restTemplate, allegroService);
    }

    @Bean
    OfferService offerService(OfferRepository offerRepository) {
        return new OfferServiceImpl(offerRepository);
    }

    @Bean
    SubscriptionsService subscriptionsRestService(SubscriptionRepository subscriptionRepository, AuthenticationService authenticationService) {
        return new SubscriptionsServiceImpl(subscriptionRepository, authenticationService);
    }

    @Bean
    UserService userService(UserRepository userRepository) {
        return new UserServiceImpl(userRepository);
    }

    @Bean
    AllegroController allegroController(AllegroOfferService allegroOfferService, AllegroCategoryService allegroCategoryService, AllegroFilterService allegroFilterService, OfferService offerService) {
        return new AllegroController(allegroOfferService, allegroCategoryService, allegroFilterService, offerService);
    }

    @Bean
    SubscriptionsRestController subscriptionsRestController(SubscriptionsService subscriptionsRestService, UserService userService) {
        return new SubscriptionsRestController(subscriptionsRestService, userService);
    }

    @Bean
    AuthenticationService authenticationService(UserRepository userRepository) {
        return new AuthenticationServiceImpl(userRepository);
    }

    @Bean
    EmailService emailService(JavaMailSender mailSender) {
        return new EmailServiceImpl(mailSender);
    }

    @Bean
    EmailController emailController(EmailService emailService, SubscriptionEmailService subscriptionEmailService) {
        return new EmailController(emailService, subscriptionEmailService);
    }

    @Bean
    SubscriptionEmailService subscriptionEmailService(AuthenticationService authenticationService, AllegroOfferService allegroOfferService, SubscriptionsService subscriptionsRestService, EmailService emailService, HtmlEmailBuilderService htmlEmailBuilderService) {
        return new SubscriptionEmailServiceImpl(authenticationService, allegroOfferService, subscriptionsRestService, emailService, htmlEmailBuilderService);
    }
}

