package pl.anitakowalczyk.surwejlansapi.service.api;

import org.springframework.http.HttpEntity;

import java.io.IOException;

public interface AllegroService {

    String getToken() throws IOException;

    HttpEntity<String> getHeaders() throws IOException;
}