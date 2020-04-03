package pl.anitakowalczyk.surwejlansapi.dao.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class AllegroCategory {

    private String id;
    private String name;
    private boolean leaf;

}
