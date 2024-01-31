package singh.mahabir.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author Mahabir Singh
 */
@Configuration
public class RestTemplateConfig {

  @Value("${customer.host}")
  private String host;

  @Bean
  public RestTemplate getRestTemplate() {
    RestTemplateBuilder builder = new RestTemplateBuilder();
    return builder.rootUri(host).build();
  }
}
