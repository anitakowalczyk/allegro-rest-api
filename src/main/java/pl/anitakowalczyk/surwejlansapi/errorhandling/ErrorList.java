package pl.anitakowalczyk.surwejlansapi.errorhandling;

import lombok.Data;

import java.util.List;

@Data
public class ErrorList {

    private List<String> errors;

    public ErrorList(List<String> errors) {
        this.errors = errors;
    }
}
