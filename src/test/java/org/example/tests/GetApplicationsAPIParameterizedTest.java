package org.example.tests;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.emptyOrNullString;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.collection.IsEmptyCollection.empty;
import static org.hamcrest.core.AnyOf.anyOf;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.hamcrest.core.IsNull.nullValue;

import java.util.List;
import java.util.stream.Stream;
import org.example.pojo.GetApplicationsResponse;
import org.example.pojo.GetApplicationsResponseData;
import org.example.utils.RequestManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class GetApplicationsAPIParameterizedTest extends BaseTest {

  private static GetApplicationsResponse response;

  @BeforeAll
  static void setup() {

    response =
        RequestManager.getRequest(
            REQ_SPEC, RESP_SPEC, "getApplications", GetApplicationsResponse.class);

    assertThat(response, notNullValue());
    assertThat(response.getTotal(), not(emptyOrNullString()));
    assertThat(response.getRequestId(), not(emptyOrNullString()));
    assertThat(response.getData(), not(empty()));
  }

  static Stream<GetApplicationsResponseData> provideApplications() {
    List<GetApplicationsResponseData> apps = response.getData();
    return apps.stream();
  }

  @ParameterizedTest
  @MethodSource("provideApplications")
  void testApplicationFields(final GetApplicationsResponseData app) {

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
