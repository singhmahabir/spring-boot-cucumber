package com.example.demo.Utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@Slf4j
public class BddUtils {
  private static final ObjectMapper mapper = new ObjectMapper();

  static {
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    mapper.registerModule(new JavaTimeModule());
    mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
  }

  public static String loadMinifiedJsonFromFile(String fileName) {
    String jsonString = loadJsonFromFile(fileName);
    try {
      return mapper.readValue(jsonString, JsonNode.class).toString();
    } catch (IOException e) {
      return jsonString;
    }
  }

  public static String getJsonString(Object object) {

    try {
      return mapper.writeValueAsString(object);
    } catch (IOException e) {
      return "";
    }
  }

  public static String loadJsonFromFile(String fileName) {
    ClassPathResource fileResource = new ClassPathResource("__files/" + fileName);
    if (fileResource.exists()) {
      try (InputStream inStream = fileResource.getInputStream()) {
        return IOUtils.toString(inStream, StandardCharsets.UTF_8);
      } catch (IOException e) {
        log.error("Unable to Read From File : " + fileName);
      }
    }
    return "";
  }

  public static <T> T convertToModelFromResponse(String jsonStr, Class<T> typeClass) {
    T response = null;
    try {
      response = mapper.readValue(jsonStr, typeClass);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return response;
  }
}
