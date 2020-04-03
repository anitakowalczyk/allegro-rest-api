package pl.anitakowalczyk.surwejlansapi.service.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Base64;

@Service
public class AllegroServiceImpl implements AllegroService {

    private final RestTemplate restTemplate;

    @Autowired
    public AllegroServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private static final String clientId = "dbd215c212f3486d844d615d366b0deb";
    private static final String clientSecret = "LGIrLqza4l2ciCddkkDUMDPKHQecsDEWbUPUPAMG9fRqZBjHx6Vcc4M2eJ4hPa0T";
    private static final String request_token_url = "https://allegro.pl/auth/oauth/token?grant_type=client_credentials";
    private static final String ACCEPT_HEADER = "application/vnd.allegro.public.v1+json";

    public String getToken() throws IOException {
        String auth = clientId + ":" + clientSecret;
        byte[] encodedAuth = Base64.getEncoder().encode(auth.getBytes(Charset.forName("US-ASCII")));
        String authHeader = "Basic " + new String(encodedAuth);
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("Authorization", authHeader);
        HttpEntity<String> request = new HttpEntity<>(headers);
        String response2 = restTemplate.exchange(request_token_url, HttpMethod.POST, request, String.class).getBody();

        ObjectMapper mapper = new ObjectMapper();
        JsonNode node;
        try {
            node = mapper.readTree(response2);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return node.path("access_token").asText();
    }

    public HttpEntity<String> getHeaders() throws IOException {
        String token = getToken();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", ACCEPT_HEADER);
        headers.add("Authorization", "Bearer " + token);
        return new HttpEntity<>(headers);
    }

}