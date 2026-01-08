package org.example.tests;

import org.example.pojo.GetApplicationsResponseData;
import org.example.pojo.GetApplicationsResponse;
import org.example.utils.RequestManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class GetApplicationsAPIParameterizedTest extends BaseTest {

    private static GetApplicationsResponse response;

    @BeforeAll
    static void setup() {

        response = RequestManager.getRequest(
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
    void testApplicationFields(GetApplicationsResponseData app) {

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
