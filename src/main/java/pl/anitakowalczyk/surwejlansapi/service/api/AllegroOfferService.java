package pl.anitakowalczyk.surwejlansapi.service.api;

import org.json.JSONException;
import pl.anitakowalczyk.surwejlansapi.dao.entity.NewOffer;
import pl.anitakowalczyk.surwejlansapi.dao.response.AllegroOfferListResponse;
import pl.anitakowalczyk.surwejlansapi.errorhandling.exception.AllegroApiFatalException;

import java.io.IOException;
import java.util.List;

public interface AllegroOfferService {

    AllegroOfferListResponse getAllegroOfferList(String param, String paramValue) throws IOException, JSONException, AllegroApiFatalException;

    AllegroOfferListResponse getAllegroOfferListByUrl(String url) throws IOException, AllegroApiFatalException;

    List<NewOffer> getAllegroOfferImageList(String param, String paramValue) throws IOException;

    List<NewOffer> getAllegroOfferImageListByUrl(String url) throws IOException;
}
