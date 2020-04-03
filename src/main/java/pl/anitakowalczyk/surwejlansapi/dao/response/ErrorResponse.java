package pl.anitakowalczyk.surwejlansapi.dao.response;

import pl.anitakowalczyk.surwejlansapi.errorhandling.Error;

import java.util.ArrayList;
import java.util.List;

public class ErrorResponse {
    public List<Error> errors = new ArrayList<>();

}
