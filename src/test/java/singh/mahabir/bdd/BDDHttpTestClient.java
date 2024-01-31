// package com.example.demo.bdd;
//
// import static io.cucumber.spring.CucumberTestContext.SCOPE_CUCUMBER_GLUE;
//
// import org.springframework.boot.test.web.client.TestRestTemplate;
// import org.springframework.boot.test.web.server.LocalServerPort;
// import org.springframework.boot.web.client.RestTemplateBuilder;
// import org.springframework.context.annotation.Scope;
// import org.springframework.stereotype.Component;
// import org.springframework.web.client.RestTemplate;
//
/// **
// * @author Mahabir Singh
// */
// @Component
// @Scope(SCOPE_CUCUMBER_GLUE)
// public class BDDHttpTestClient {
//
//  private final String SERVER_URL = "http://localhost";
//
//  @LocalServerPort private int port;
//
//  private RestTemplate restTemplate;
//
//  private TestRestTemplate testRestTemplate;
//
//  public RestTemplate getRestTemplate() {
//    if (restTemplate == null) {
//      restTemplate = new RestTemplateBuilder().rootUri(SERVER_URL + ":" + port).build();
//    }
//    return restTemplate;
//  }
//
//  public TestRestTemplate getTestRestTemplate() {
//    if (testRestTemplate == null) {
//      testRestTemplate =
//          new TestRestTemplate(new RestTemplateBuilder().rootUri(SERVER_URL + ":" + port));
//    }
//    return testRestTemplate;
//  }
// }
