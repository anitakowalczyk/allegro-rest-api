package pl.anitakowalczyk.surwejlansapi.dao.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AllegroFilterValue {

    private String name;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String value;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String idSuffix;

}
