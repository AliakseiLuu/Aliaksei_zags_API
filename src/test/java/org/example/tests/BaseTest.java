package org.example.tests;

import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.example.spec.SpecConfig;

public class BaseTest {

  protected static final RequestSpecification REQ_SPEC = SpecConfig.requestSpecification();
  protected static final ResponseSpecification RESP_SPEC = SpecConfig.responseSpecification();
}
