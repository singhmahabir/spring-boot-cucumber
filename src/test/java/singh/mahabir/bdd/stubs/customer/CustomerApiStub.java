package singh.mahabir.bdd.stubs.customer;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.equalToJson;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;

import singh.mahabir.Utils.BddUtils;

public class CustomerApiStub {

  private static final String API_URL = "/bdd/any-api-which-needs-to-mock";
  private static final String FILE_BASE_PATH = "customer/";

  public static void addValidReturnReceiptResponse() {
    stubFor(
        post(urlEqualTo(API_URL))
            .withRequestBody(
                equalToJson(
                    BddUtils.loadJsonFromFile(FILE_BASE_PATH + "request-01932488.json"),
                    true,
                    true))
            .willReturn(
                aResponse()
                    .withStatus(200)
                    .withHeader("Content-Type", "application/json")
                    .withBodyFile(FILE_BASE_PATH + "response-01932488.json")));
  }
}
