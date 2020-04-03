package pl.anitakowalczyk.surwejlansapi.service.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import pl.anitakowalczyk.surwejlansapi.dao.entity.AllegroFilter;
import pl.anitakowalczyk.surwejlansapi.dao.response.AllegroFilterListResponse;
import pl.anitakowalczyk.surwejlansapi.dao.response.ErrorResponse;
import pl.anitakowalczyk.surwejlansapi.errorhandling.exception.AllegroApiFatalException;

import java.io.IOException;
import java.util.List;

@Service
public class AllegroFilterServiceImpl implements AllegroFilterService {

    private final RestTemplate restTemplate;
    private final AllegroService allegroService;

    @Autowired
    public AllegroFilterServiceImpl(RestTemplate restTemplate, AllegroService allegroService) {
        this.restTemplate = restTemplate;
        this.allegroService = allegroService;
    }

    public List<AllegroFilter> getAllegroFilters(String phrase) throws IOException, AllegroApiFatalException {
        String url = "https://api.allegro.pl/offers/listing?phrase=" + phrase + "&fallback=true&include=-all&include=filters";
        try {
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, allegroService.getHeaders(), String.class);
            return new ObjectMapper().readValue(response.getBody(), AllegroFilterListResponse.class).filters;
        } catch (HttpStatusCodeException e) {
            throw new AllegroApiFatalException(new ObjectMapper().readValue(e.getResponseBodyAsString(), ErrorResponse.class).errors.get(0).getMessage());
        }
    }
}
