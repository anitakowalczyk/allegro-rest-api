package pl.anitakowalczyk.surwejlansapi.service.database;

import pl.anitakowalczyk.surwejlansapi.dao.entity.AllegroOffer;

import java.util.List;

public interface OfferService {

    Iterable<AllegroOffer> findAll();

    AllegroOffer save(AllegroOffer allegroOffer);

    void saveOffers(List<AllegroOffer> allegroOffers);

}
