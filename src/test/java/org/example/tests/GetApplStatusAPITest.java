package org.example.tests;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.matchesPattern;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.text.IsEmptyString.emptyOrNullString;

import org.example.pojo.api.GetApplStatusResponse;
import org.example.pojo.api.GetApplStatusResponseData;
import org.example.pojo.api.GetApplicationsResponse;
import org.example.utils.Endpoints;
import org.example.utils.RequestManager;
import org.junit.jupiter.api.Test;

public class GetApplStatusAPITest extends BaseTest {

  @Test
  public void getApplStatus() {

    GetApplicationsResponse applications =
        RequestManager.getRequest(
            REQ_SPEC, RESP_SPEC, Endpoints.GET_APPLICATIONS, GetApplicationsResponse.class);

    Integer applicationId = applications.getData().get(0).getApplicationid();

    GetApplStatusResponse response =
        RequestManager.getRequest(
            REQ_SPEC,
            RESP_SPEC,
            Endpoints.GET_APPL_STATUS + applicationId,
            GetApplStatusResponse.class);

    assertThat(response, notNullValue());
    assertThat(response.getRequestId(), not(emptyOrNullString()));
    assertThat(response.getData(), notNullValue());

    GetApplStatusResponseData app = response.getData();
    assertThat(
        app.getDateofapplication(),
        allOf(
            not(emptyOrNullString()),
            matchesPattern("^\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}\\.\\d{3}Z$")));
    assertThat(app.getKindofapplication(), not(emptyOrNullString()));
    assertThat(app.getStatusofapplication(), not(emptyOrNullString()));
  }
}
