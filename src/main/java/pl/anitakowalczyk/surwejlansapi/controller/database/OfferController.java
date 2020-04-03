package pl.anitakowalczyk.surwejlansapi.controller.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.anitakowalczyk.surwejlansapi.dao.entity.AllegroOffer;
import pl.anitakowalczyk.surwejlansapi.service.database.OfferService;

@RestController
@RequestMapping("/database")
public class OfferController {

    @Autowired
    private OfferService offerService;

    @Autowired
    public OfferController(OfferService offerService) {
        this.offerService = offerService;
    }

    @GetMapping("/all")
    public Iterable<AllegroOffer> getAll() {
        return offerService.findAll();
    }

}
