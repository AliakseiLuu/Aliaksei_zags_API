package org.example.tests;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.collection.IsEmptyCollection.empty;
import static org.hamcrest.core.AnyOf.anyOf;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.hamcrest.core.IsNull.nullValue;
import static org.hamcrest.number.OrderingComparison.greaterThan;
import static org.hamcrest.text.IsEmptyString.emptyOrNullString;
import static org.hamcrest.text.MatchesPattern.matchesPattern;

import java.util.Random;
import org.example.pojo.api.GetApplicationsResponse;
import org.example.pojo.api.GetApplicationsResponseData;
import org.example.utils.Endpoints;
import org.example.utils.RequestManager;
import org.junit.jupiter.api.Test;

public class GetApplicationsAPIForRandomApplicationTest extends BaseTest {

  @Test
  public void getApplications() {

    GetApplicationsResponse response =
        RequestManager.getRequest(
            REQ_SPEC, RESP_SPEC, Endpoints.GET_APPLICATIONS, GetApplicationsResponse.class);

    assertThat(response, notNullValue());
    assertThat(response.getTotal(), not(emptyOrNullString()));
    assertThat(response.getRequestId(), not(emptyOrNullString()));
    assertThat(response.getData(), not(empty()));

    int size = response.getData().size();

    Random random = new Random();
    int randomIndex = random.nextInt(size);

    GetApplicationsResponseData app = response.getData().get(randomIndex);
    assertThat(app.getApplicationid(), greaterThan(0));
    assertThat(app.getCitizenid(), greaterThan(0));
    assertThat(app.getApplicantid(), greaterThan(0));
    assertThat(app.getStaffid(), anyOf(nullValue(), instanceOf(Integer.class)));
    assertThat(
        app.getDateOfApplication(),
        allOf(
            not(emptyOrNullString()),
            matchesPattern("^\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}\\.\\d{3}Z$")));
    assertThat(app.getKindOfApplication(), not(emptyOrNullString()));
    assertThat(app.getStatusOfApplication(), not(emptyOrNullString()));
    assertThat(app.getChannel(), not(emptyOrNullString()));
    assertThat(app.getImage(), anyOf(nullValue(), instanceOf(String.class)));
  }
}
