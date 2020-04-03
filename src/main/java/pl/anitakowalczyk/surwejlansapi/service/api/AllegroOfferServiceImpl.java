package pl.anitakowalczyk.surwejlansapi.service.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import pl.anitakowalczyk.surwejlansapi.dao.entity.NewOffer;
import pl.anitakowalczyk.surwejlansapi.dao.response.AllegroOfferListResponse;
import pl.anitakowalczyk.surwejlansapi.dao.response.ErrorResponse;
import pl.anitakowalczyk.surwejlansapi.errorhandling.exception.AllegroApiFatalException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class AllegroOfferServiceImpl implements AllegroOfferService {

    private final RestTemplate restTemplate;
    private final AllegroService allegroService;

    @Autowired
    public AllegroOfferServiceImpl(RestTemplate restTemplate, AllegroService allegroService) {
        this.restTemplate = restTemplate;
        this.allegroService = allegroService;
    }

    public AllegroOfferListResponse getAllegroOfferList(String param, String paramValue) throws IOException, AllegroApiFatalException {
        String url = "https://api.allegro.pl/offers/listing?" + param + "=" + paramValue;
        return getAllegroOfferListByUrl(url);
    }

    public AllegroOfferListResponse getAllegroOfferListByUrl(String url) throws IOException, AllegroApiFatalException {
        try {
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, allegroService.getHeaders(), String.class);
            JSONObject jsonObject = new JSONObject(response.getBody()).getJSONObject("items");
            return new ObjectMapper().readValue(jsonObject.toString(), AllegroOfferListResponse.class);
        } catch (HttpStatusCodeException e) {
            throw new AllegroApiFatalException(new ObjectMapper().readValue(e.getResponseBodyAsString(), ErrorResponse.class).errors.get(0).getMessage());
        }
    }

    public List<NewOffer> getAllegroOfferImageList(String param, String paramValue) throws IOException {
        String url = "https://api.allegro.pl/offers/listing?" + param + "=" + paramValue;
        return getAllegroOfferImageListByUrl(url);
    }

    public List<NewOffer> getAllegroOfferImageListByUrl(String url) throws IOException {
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, allegroService.getHeaders(), String.class);

        List<NewOffer> offers = new ArrayList<>();
        String json = response.toString();
        JSONObject jsonObj = new JSONObject(json.substring(json.indexOf("{"), json.lastIndexOf("}") + 1));
        String type[] = {"promoted", "regular"};
        JSONObject items = jsonObj.getJSONObject("items");
        if (items != null) {
            for (int j = 0; j < type.length; j++) {
                JSONArray jsonArray = items.getJSONArray(type[j]);
                if (jsonArray != null) {
                    for (int i = 0; i < jsonArray.length(); i++) {
                        offers.add(new NewOffer(
                                jsonArray.getJSONObject(i).getString("id"), jsonArray.getJSONObject(i).getString("name"),
                                jsonArray.getJSONObject(i).getJSONArray("images").getJSONObject(0).getString("url")));
                    }
                }
            }
        }
        return offers;
    }


}
