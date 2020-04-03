package pl.anitakowalczyk.surwejlansapi.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.anitakowalczyk.surwejlansapi.dao.entity.AllegroCategory;
import pl.anitakowalczyk.surwejlansapi.dao.entity.AllegroFilter;
import pl.anitakowalczyk.surwejlansapi.dao.entity.AllegroOffer;
import pl.anitakowalczyk.surwejlansapi.dao.entity.NewOffer;
import pl.anitakowalczyk.surwejlansapi.dao.response.AllegroOfferListResponse;
import pl.anitakowalczyk.surwejlansapi.errorhandling.exception.AllegroApiFatalException;
import pl.anitakowalczyk.surwejlansapi.service.api.AllegroCategoryService;
import pl.anitakowalczyk.surwejlansapi.service.api.AllegroFilterService;
import pl.anitakowalczyk.surwejlansapi.service.api.AllegroOfferService;
import pl.anitakowalczyk.surwejlansapi.service.database.OfferService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class AllegroController {

    private AllegroOfferService allegroOfferService;
    private AllegroCategoryService allegroCategoryService;
    private AllegroFilterService allegroFilterService;
    private OfferService offerService;

    @Autowired
    public AllegroController(AllegroOfferService allegroOfferService, AllegroCategoryService allegroCategoryService, AllegroFilterService allegroFilterService, OfferService offerService) {
        this.allegroOfferService = allegroOfferService;
        this.allegroCategoryService = allegroCategoryService;
        this.allegroFilterService = allegroFilterService;
        this.offerService = offerService;
    }

    @GetMapping(value = {"/categories", "/categories/{parentId}"})
    public List<AllegroCategory> getCategories(@PathVariable Optional<String> parentId) throws IOException, AllegroApiFatalException {
        return allegroCategoryService.getAllegroCategoryList(parentId);
    }

    @GetMapping("/offers/categoryId/{categoryId}")
    public List<AllegroOffer> getAllegroOffersByCategoryId(@PathVariable String categoryId) throws IOException, AllegroApiFatalException {
        return allegroOfferService.getAllegroOfferList("category.id", categoryId).regular;
    }

    @GetMapping("/offersImage/phrase/{phrase}")
    public List<NewOffer> getAllegroOfferImageList(@PathVariable String phrase) throws IOException {
        List<NewOffer> offers = allegroOfferService.getAllegroOfferImageList("phrase", phrase);
        return offers;
    }

    @GetMapping("/offers/phrase/{phrase}")
    public List<AllegroOffer> getAllegroOffersByPhrase(@PathVariable String phrase) throws IOException, AllegroApiFatalException {
        AllegroOfferListResponse items = allegroOfferService.getAllegroOfferList("phrase", phrase);
        offerService.saveOffers(items.regular);
        return items.regular;
    }

    @GetMapping("/offers/phrase/{phrase}/filters")
    public List<AllegroFilter> getFilters(@PathVariable String phrase) throws IOException, AllegroApiFatalException {
        return allegroFilterService.getAllegroFilters(phrase);
    }
}