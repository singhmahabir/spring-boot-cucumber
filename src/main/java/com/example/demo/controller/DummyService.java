package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * @author Mahabir Singh
 */
@Service
public class DummyService {

  private static final String DUMMY_POST_API = "/bdd/any-api-which-needs-to-mock";

  @Autowired RestTemplate template;

  public String getName(String name) {
    return "Hello " + name;
  }

  public MyResponse callApi(MyRequest request) {
    UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(DUMMY_POST_API);
    HttpEntity<MyRequest> reqEntity = new HttpEntity<>(request);

    return template.postForEntity(builder.toUriString(), reqEntity, MyResponse.class).getBody();
  }
}
