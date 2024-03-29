package singh.mahabir.bdd.stepdefs.customer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static singh.mahabir.bdd.stubs.customer.CustomerApiStub.addValidReturnReceiptResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;
import singh.mahabir.Utils.BddUtils;
import singh.mahabir.dto.MyRequest;
import singh.mahabir.dto.MyResponse;

public class CustomerStepDef {

  private static final String DUMMY_POST_API = "/api/post";
  private static final String DUMMY_GET_API = "/api/get";
  private static final String FILE_BASE_PATH = "customer/";

  private MyRequest myRequest;
  private ResponseEntity<MyResponse> postResponseEntity;
  private ResponseEntity<String> getResponseEntity;

  @Autowired private TestRestTemplate template;

  private String name;

  @Before("@CucumberFeatureTest")
  public void init() {
    addValidReturnReceiptResponse();
  }

  @Given("^create cucumber post request$")
  public void prepareToCallDummyAPI(DataTable table) throws JsonProcessingException {
    String requestFileName = table.asLists().get(1).get(0);
    String requestString = BddUtils.loadJsonFromFile(FILE_BASE_PATH + requestFileName);
    myRequest = new ObjectMapper().readValue(requestString, MyRequest.class);
  }

  @When("^call cucumber post API$")
  public void callCreateAdjustmentAPI() {
    UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(DUMMY_POST_API);
    HttpEntity<MyRequest> reqEntity = prepareRequestEntity();
    postResponseEntity = template.postForEntity(builder.toUriString(), reqEntity, MyResponse.class);
  }

  @Then("^validate cucumber post API response$")
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

  @Given("create cucumber get request {string}")
  public void createDummyGetRequest(String requestParam) {
    name = requestParam;
  }

  @When("call cucumber get API")
  public void callGeorgeCreateAdjustmentAPI() {
    UriComponentsBuilder builder =
        UriComponentsBuilder.fromUriString(DUMMY_GET_API).queryParam("name", name);
    getResponseEntity = template.getForEntity(builder.toUriString(), String.class);
  }

  @Then("validate cucumber get API response")
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
