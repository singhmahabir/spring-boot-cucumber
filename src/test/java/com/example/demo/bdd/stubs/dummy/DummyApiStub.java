package com.example.demo.bdd.stubs.dummy;

import static com.example.demo.Utils.BddUtils.loadJsonFromFile;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.equalToJson;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;

public class DummyApiStub {

  private static final String API_URL = "/bdd/any-api-which-needs-to-mock";
  private static final String FILE_BASE_PATH = "dummy/";

  public static void addValidReturnReceiptResponse() {
    stubFor(
        post(urlEqualTo(API_URL))
            .withRequestBody(
                equalToJson(loadJsonFromFile(FILE_BASE_PATH + "request-01932488.json"), true, true))
            .willReturn(
                aResponse()
                    .withStatus(200)
                    .withHeader("Content-Type", "application/json")
                    .withBodyFile(FILE_BASE_PATH + "response-01932488.json")));
  }
}
