package com.example.demo.bdd.stepdefs.dummy;

import static com.example.demo.bdd.stubs.dummy.DummyApiStub.addValidReturnReceiptResponse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.example.demo.Utils.BddUtils;
import com.example.demo.controller.MyRequest;
import com.example.demo.controller.MyResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

public class DummyStepDef {

  private static final String DUMMY_POST_API = "/api/post";
  private static final String DUMMY_GET_API = "/api/get";
  private static final String FILE_BASE_PATH = "dummy/";

  private MyRequest myRequest;
  private ResponseEntity<MyResponse> postResponseEntity;
  private ResponseEntity<String> getResponseEntity;

  @Autowired private TestRestTemplate template;

  private String name;

  @Before("@DummyFeatureTest")
  public void init() {
    addValidReturnReceiptResponse();
  }

  @Given("^create dummy post request$")
  public void prepareToCallDummyAPI(DataTable table) throws JsonProcessingException {
    String requestFileName = table.asLists().get(1).get(0);
    String requestString = BddUtils.loadJsonFromFile(FILE_BASE_PATH + requestFileName);
    myRequest = new ObjectMapper().readValue(requestString, MyRequest.class);
  }

  @When("^call dummy post API$")
  public void callCreateAdjustmentAPI() {
    UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(DUMMY_POST_API);
    HttpEntity<MyRequest> reqEntity = prepareRequestEntity();
    postResponseEntity = template.postForEntity(builder.toUriString(), reqEntity, MyResponse.class);
  }

  @Then("^validate dummy post API response$")
  public void validateCreateAdjustmentAPIResponse(DataTable table) {
    List<String> values = table.asLists().get(1);
    String expectedHttpStatus = values.get(0);
    String expectedAge = values.get(1);
    String expectedName = values.get(2);

    String actualStatusCode = String.valueOf(postResponseEntity.getStatusCode().value());
    MyResponse response = postResponseEntity.getBody();
    assertEquals(expectedHttpStatus, actualStatusCode);
    assertNotNull(response);
    assertNotNull(response.getAge());
    assertEquals(expectedAge, String.valueOf(response.getAge()));
    assertEquals(expectedName, response.getName());
  }

  @Given("create dummy get request {string}")
  public void createDummyGetRequest(String requestParam) {
    name = requestParam;
  }

  @When("call dummy get API")
  public void callGeorgeCreateAdjustmentAPI() {
    UriComponentsBuilder builder =
        UriComponentsBuilder.fromUriString(DUMMY_GET_API).queryParam("name", name);
    getResponseEntity = template.getForEntity(builder.toUriString(), String.class);
  }

  @Then("validate dummy get API response")
  public void validateDummyGetAPIResponse(DataTable table) {
    List<String> values = table.asLists().get(1);
    String expectedHttpStatus = values.get(0);
    String expectedResponse = values.get(1);
    String actualStatusCode = String.valueOf(getResponseEntity.getStatusCode().value());
    String response = getResponseEntity.getBody();
    assertEquals(expectedHttpStatus, actualStatusCode);
    assertEquals(expectedResponse, response);
  }

  private HttpEntity<MyRequest> prepareRequestEntity() {
    return new HttpEntity<>(myRequest);
  }
}
