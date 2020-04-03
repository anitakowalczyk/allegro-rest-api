package pl.anitakowalczyk.surwejlansapi.service.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.anitakowalczyk.surwejlansapi.dao.entity.AllegroOffer;
import pl.anitakowalczyk.surwejlansapi.dao.repository.OfferRepository;

import java.util.List;

@Service
public class OfferServiceImpl implements OfferService {

    private final OfferRepository offerRepository;

    @Autowired
    public OfferServiceImpl(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    public Iterable<AllegroOffer> findAll() {
        return offerRepository.findAll();
    }

    public AllegroOffer save(AllegroOffer allegroOffer) {
        return offerRepository.save(allegroOffer);
    }

    public void saveOffers(List<AllegroOffer> allegroOffers) {
        allegroOffers.forEach(offer -> save(offer));
    }
}
