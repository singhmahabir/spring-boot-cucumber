package singh.mahabir.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import singh.mahabir.dto.MyRequest;
import singh.mahabir.dto.MyResponse;
import singh.mahabir.service.CustomerService;

/**
 * @author Mahabir Singh
 */
@RestController
@RequestMapping("api")
@RequiredArgsConstructor
@Slf4j
public class CustomerController {

  final CustomerService customerService;

  @GetMapping("/get")
  public String getValue(@RequestParam String name) {

    return customerService.getName(name);
  }

  @PostMapping("post")
  public MyResponse post(@RequestBody MyRequest request) {
    log.info("request received {}", request);

    return customerService.callApi(request);
  }
}
