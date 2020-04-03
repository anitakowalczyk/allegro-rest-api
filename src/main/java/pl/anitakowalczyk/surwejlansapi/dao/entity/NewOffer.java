package pl.anitakowalczyk.surwejlansapi.dao.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class NewOffer {

    private String allegroId;
    private String name;
    private String imageUrl;

    public NewOffer(String allegroId, String name, String imageUrl) {
        this.allegroId = allegroId;
        this.name = name;
        this.imageUrl = imageUrl;
    }
}
