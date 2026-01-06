package org.example.tests;

import org.example.pojo.GetApplicationResponse;
import org.example.utils.RequestManager;
import org.junit.jupiter.api.Test;

public class GetApplications extends BaseTest {

  @Test
  public void getApplications() {

    GetApplicationResponse response =
        RequestManager.getRequest(
            REQ_SPEC, RESP_SPEC, "getApplications", GetApplicationResponse.class);
  }
}
