package pl.anitakowalczyk.surwejlansapi.service.email;

import org.springframework.beans.factory.annotation.Autowired;
import pl.anitakowalczyk.surwejlansapi.dao.entity.Subscription;
import pl.anitakowalczyk.surwejlansapi.errorhandling.exception.AllegroApiFatalException;
import pl.anitakowalczyk.surwejlansapi.errorhandling.exception.ConnectException;
import pl.anitakowalczyk.surwejlansapi.errorhandling.exception.IllegalAccessException;
import pl.anitakowalczyk.surwejlansapi.errorhandling.exception.NotFoundException;
import pl.anitakowalczyk.surwejlansapi.service.api.AllegroOfferService;
import pl.anitakowalczyk.surwejlansapi.service.api.AuthenticationService;
import pl.anitakowalczyk.surwejlansapi.service.api.SubscriptionsService;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SubscriptionEmailServiceImpl implements SubscriptionEmailService {

    private final AuthenticationService authenticationService;
    private final AllegroOfferService allegroOfferService;
    private final SubscriptionsService subscriptionsService;
    private final EmailService emailService;
    private final HtmlEmailBuilderService htmlEmailBuilderService;

    @Autowired
    public SubscriptionEmailServiceImpl(AuthenticationService authenticationService, AllegroOfferService allegroOfferService, SubscriptionsService subscriptionsService, EmailService emailService, HtmlEmailBuilderService htmlEmailBuilderService) {
        this.authenticationService = authenticationService;
        this.allegroOfferService = allegroOfferService;
        this.subscriptionsService = subscriptionsService;
        this.emailService = emailService;
        this.htmlEmailBuilderService = htmlEmailBuilderService;
    }

    private boolean isGiven(String val) {
        return !"null".equals(val) && val != null && val.length() > 0;
    }

    private String getParameter(String param, String paramValue) {
        return param + "=" + paramValue.replaceAll("\\s", "+");
    }

    public String buildUrl(Subscription subscription) {
        Map<String, String> paramsMap = Stream
                .of(
                        Arrays.asList("category.id", subscription.getCategoryId()),
                        Arrays.asList("phrase", subscription.getPhrase()),
                        Arrays.asList("seller.id", subscription.getSellerId())
                )
                .collect(Collectors.toMap(data -> data.get(0), data -> data.get(1)));

        return "https://api.allegro.pl/offers/listing?" + paramsMap.entrySet().stream()
                .map(e -> isGiven(e.getValue()) ? getParameter(e.getKey(), e.getValue()) : null)
                .filter(Objects::nonNull)
                .collect(Collectors.joining("&"));
    }

    public void send(Integer subscriptionId) throws NotFoundException, IllegalAccessException, AllegroApiFatalException, IOException, ConnectException {
        String url = buildUrl(subscriptionsService.findSubscriptionById(subscriptionId));
        // String html = htmlEmailBuilderService.buildOfferListEmail(allegroOfferService.getAllegroOfferListByUrl(url)); //without images
        String html = htmlEmailBuilderService.buildOfferImageListEmail(allegroOfferService.getAllegroOfferImageListByUrl(url));
        try {
            emailService.sendSubscriptionEmail(new Email(authenticationService.getCurrentUser().getEmail(), "Offer list", html));
        } catch (Exception e) {
            throw new ConnectException(e.getCause().getMessage());
        }
    }
}