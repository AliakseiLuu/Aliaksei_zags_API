package org.example.spec;

import org.example.utils.Config;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import lombok.experimental.UtilityClass;

@UtilityClass
public class SpecConfig {

    public RequestSpecification requestSpecification() {
        return new RequestSpecBuilder()
                .setBaseUri(Config.get("baseUri"))
                .setAuth(RestAssured.basic(Config.get("zagsUsername"), Config.get("zagsPassword")))
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();
    }

    public ResponseSpecification responseSpecification() {
        return new ResponseSpecBuilder()
                .log(LogDetail.ALL)
                .expectStatusCode(200)
                .build();
    }
}
