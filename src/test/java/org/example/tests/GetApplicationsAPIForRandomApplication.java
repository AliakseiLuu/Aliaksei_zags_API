package org.example.tests;

import org.example.pojo.GetApplicationsResponseData;
import org.example.pojo.GetApplicationsResponse;
import org.example.utils.RequestManager;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class GetApplicationsAPIForRandomApplication extends BaseTest {

  @Test
  public void getApplications() {

    GetApplicationsResponse response =
        RequestManager.getRequest(
            REQ_SPEC, RESP_SPEC, "getApplications", GetApplicationsResponse.class);

    assertThat(response, notNullValue());
    assertThat(response.getTotal(), not(emptyOrNullString()));
    assertThat(response.getRequestId(), not(emptyOrNullString()));
    assertThat(response.getData(), not(empty()));

    int size = response.getData().size();

    Random random = new Random();
    int randomIndex = random.nextInt(size);

    GetApplicationsResponseData app = response.getData().get(randomIndex);
    assertThat(app.getApplicationid(), notNullValue());
    assertThat(app.getCitizenid(), notNullValue());
    assertThat(app.getApplicantid(), notNullValue());
    assertThat(app.getStaffid(), anyOf(nullValue(), instanceOf(Integer.class)));
    assertThat(app.getDateOfApplication(), not(emptyOrNullString()));
    assertThat(app.getKindOfApplication(), not(emptyOrNullString()));
    assertThat(app.getStatusOfApplication(), not(emptyOrNullString()));
    assertThat(app.getChannel(), not(emptyOrNullString()));
    assertThat(app.getImage(), anyOf(nullValue(), instanceOf(String.class)));
  }
}
