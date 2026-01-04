package org.example.tests;

import io.restassured.response.Response;
import org.example.pojo.GetApplicationResponse;
import org.example.utils.RequestManager;
import org.junit.jupiter.api.Test;
import org.example.spec.SpecConfig;

import static io.restassured.RestAssured.*;

public class getApplications {

    @Test
    public void getApplications() {

        GetApplicationResponse response =
                given()
                    .spec(SpecConfig.requestSpecification())
                    .basePath("getApplications")
                .when()
                    .get()
                .then()
                    .spec(SpecConfig.responseSpecification())
                    .extract().as(GetApplicationResponse.class);
    }
}
