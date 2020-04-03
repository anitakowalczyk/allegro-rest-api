package pl.anitakowalczyk.surwejlansapi.service.api;

import pl.anitakowalczyk.surwejlansapi.dao.entity.AllegroFilter;
import pl.anitakowalczyk.surwejlansapi.errorhandling.exception.AllegroApiFatalException;

import java.io.IOException;
import java.util.List;

public interface AllegroFilterService {

    List<AllegroFilter> getAllegroFilters(String phrase) throws IOException, AllegroApiFatalException;
}
