package pl.anitakowalczyk.surwejlansapi.dao.response;

import pl.anitakowalczyk.surwejlansapi.dao.entity.AllegroOffer;

import java.util.ArrayList;
import java.util.List;

public class AllegroOfferListResponse {
    public List<AllegroOffer> promoted = new ArrayList<>();
    public List<AllegroOffer> regular = new ArrayList<>();
}
