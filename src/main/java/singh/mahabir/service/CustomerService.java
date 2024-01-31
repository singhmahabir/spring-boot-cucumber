package singh.mahabir.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import singh.mahabir.dto.MyRequest;
import singh.mahabir.dto.MyResponse;

/**
 * @author Mahabir Singh
 */
@Service
@RequiredArgsConstructor
public class CustomerService {

  private static final String DUMMY_POST_API = "/bdd/any-api-which-needs-to-mock";

 final RestTemplate template;

  public String getName(String name) {
    return "Hello " + name;
  }

  public MyResponse callApi(MyRequest request) {
    UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(DUMMY_POST_API);
    HttpEntity<MyRequest> reqEntity = new HttpEntity<>(request);

    return template.postForEntity(builder.toUriString(), reqEntity, MyResponse.class).getBody();
  }

    public String getCustomerId(String id) {
        return "Customer id " + id;
    }


}
