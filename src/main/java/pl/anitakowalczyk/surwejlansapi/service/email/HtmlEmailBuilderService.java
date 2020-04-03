package pl.anitakowalczyk.surwejlansapi.service.email;

import pl.anitakowalczyk.surwejlansapi.dao.entity.NewOffer;
import pl.anitakowalczyk.surwejlansapi.dao.response.AllegroOfferListResponse;

import java.util.List;

public interface HtmlEmailBuilderService {

    String buildOfferListEmail(AllegroOfferListResponse allegroOfferList);

    String buildOfferImageListEmail(List<NewOffer> offers);
}
