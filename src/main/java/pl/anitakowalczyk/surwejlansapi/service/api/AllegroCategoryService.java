package pl.anitakowalczyk.surwejlansapi.service.api;

import pl.anitakowalczyk.surwejlansapi.dao.entity.AllegroCategory;
import pl.anitakowalczyk.surwejlansapi.errorhandling.exception.AllegroApiFatalException;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface AllegroCategoryService {

    List<AllegroCategory> getAllegroCategoryList(Optional<String> parentId) throws IOException, AllegroApiFatalException;

}
