package org.example.tests;

import org.example.pojo.GetApplStatusResponse;
import org.example.pojo.GetApplicationResponse;
import org.example.utils.RequestManager;
import org.junit.jupiter.api.Test;

public class GetApplStatus extends BaseTest {

  @Test
  public void getApplStatus() {

    GetApplicationResponse applications =
        RequestManager.getRequest(
            REQ_SPEC, RESP_SPEC, "getApplications", GetApplicationResponse.class);

    Integer applicationId = applications.getData().get(0).getApplicationid();

    GetApplStatusResponse response =
        RequestManager.getRequest(
            REQ_SPEC, RESP_SPEC, "getApplStatus/" + applicationId, GetApplStatusResponse.class);
  }
}
