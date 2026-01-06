package org.example.utils;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import lombok.experimental.UtilityClass;

@UtilityClass
public class RequestManager {

  public <T> T getRequest(
      final RequestSpecification requestSpecification,
      final ResponseSpecification responseSpecification,
      final String path,
      final Class<T> tClass) {
    return RestAssured.given()
        .spec(requestSpecification)
        .basePath(path)
        .when()
        .get()
        .then()
        .spec(responseSpecification)
        .extract()
        .as(tClass);
  }
}
