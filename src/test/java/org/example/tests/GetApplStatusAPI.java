package org.example.tests;

import org.example.pojo.GetApplStatusResponseData;
import org.example.pojo.GetApplStatusResponse;
import org.example.pojo.GetApplicationsResponse;
import org.example.utils.RequestManager;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class GetApplStatusAPI extends BaseTest {

  @Test
  public void getApplStatus() {

    GetApplicationsResponse applications =
        RequestManager.getRequest(
            REQ_SPEC, RESP_SPEC, "getApplications", GetApplicationsResponse.class);

    Integer applicationId = applications.getData().get(0).getApplicationid();

    GetApplStatusResponse response =
        RequestManager.getRequest(
            REQ_SPEC, RESP_SPEC, "getApplStatus/" + applicationId, GetApplStatusResponse.class);

      assertThat(response, notNullValue());
      assertThat(response.getRequestId(), not(emptyOrNullString()));
      assertThat(response.getData(), notNullValue());

      GetApplStatusResponseData app = response.getData();
      assertThat(app.getDateofapplication(), notNullValue());
      assertThat(app.getKindofapplication(), notNullValue());
      assertThat(app.getStatusofapplication(), notNullValue());
  }
}
