package pl.anitakowalczyk.surwejlansapi.dao.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AllegroFilter {

    private String id;
    private String name;
    private List<AllegroFilterValue> values;

}
