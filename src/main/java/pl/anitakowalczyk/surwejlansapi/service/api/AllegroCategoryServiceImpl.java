package pl.anitakowalczyk.surwejlansapi.service.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import pl.anitakowalczyk.surwejlansapi.dao.entity.AllegroCategory;
import pl.anitakowalczyk.surwejlansapi.dao.response.AllegroCategoriesListResponse;
import pl.anitakowalczyk.surwejlansapi.dao.response.ErrorResponse;
import pl.anitakowalczyk.surwejlansapi.errorhandling.exception.AllegroApiFatalException;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class AllegroCategoryServiceImpl implements AllegroCategoryService {

    private final RestTemplate restTemplate;
    private final AllegroService allegroService;

    @Autowired
    public AllegroCategoryServiceImpl(RestTemplate restTemplate, AllegroService allegroService) {
        this.restTemplate = restTemplate;
        this.allegroService = allegroService;
    }

    private List<AllegroCategory> getAllegroResponse(String url) throws IOException, AllegroApiFatalException {
        try {
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, allegroService.getHeaders(), String.class);
            return new ObjectMapper().readValue(response.getBody(), AllegroCategoriesListResponse.class).categories;
        } catch (HttpStatusCodeException e) {
            throw new AllegroApiFatalException(new ObjectMapper().readValue(e.getResponseBodyAsString(), ErrorResponse.class).errors.get(0).getMessage());
        }
    }

    public List<AllegroCategory> getAllegroCategoryList(Optional<String> parentId) throws IOException, AllegroApiFatalException {
        if (parentId.isPresent()) {
            return getAllegroResponse("https://api.allegro.pl/sale/categories?parent.id=" + parentId.get());
        } else {
            return getAllegroResponse("https://api.allegro.pl/sale/categories");
        }
    }
}