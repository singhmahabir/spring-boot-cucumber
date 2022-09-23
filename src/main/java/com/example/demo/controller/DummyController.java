package com.example.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Mahabir Singh
 */
@RestController
@RequestMapping("api")
@Slf4j
public class DummyController {

  @Autowired
  private DummyService dummyService;

  @GetMapping("/get")
  public String getValue(@RequestParam String name) {

    return dummyService.getName(name);
  }

  @PostMapping("post")
  public MyResponse post(@RequestBody MyRequest request) {
    log.info("request received {}", request);

    return dummyService.callApi(request);
  }
}
