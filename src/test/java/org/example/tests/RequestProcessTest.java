package org.example.tests;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.matchesPattern;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.core.AnyOf.anyOf;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.hamcrest.core.IsNull.nullValue;
import static org.hamcrest.number.OrderingComparison.greaterThan;
import static org.hamcrest.text.IsEmptyString.emptyOrNullString;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Random;
import net.datafaker.Faker;
import org.example.pojo.SendRequestProcessData;
import org.example.pojo.SendRequestProcessRequest;
import org.example.pojo.SendRequestProcessResponse;
import org.example.pojo.GetApplicationsResponse;
import org.example.pojo.SendAdminRequest;
import org.example.pojo.SendAdminResponse;
import org.example.pojo.GetApplicationsResponseData;
import org.example.utils.Endpoints;
import org.example.utils.RequestManager;
import org.junit.jupiter.api.Test;

public class RequestProcessTest extends BaseTest {

  @Test
  public void requestProcessApprovedTest() {

    SendRequestProcessRequest request =
        SendRequestProcessRequest.builder()
            .staffid(sendAdminRequestForStuffId())
            .applId(getApplicationsForApplId("rejected"))
            .action("approved")
            .build();

    SendRequestProcessResponse response =
        RequestManager.postRequest(
            REQ_SPEC,
            RESP_SPEC,
            Endpoints.REQUEST_PROCESS,
            request,
            SendRequestProcessResponse.class);

    System.out.println("Response" + response);

    assertThat(response, notNullValue());
    assertThat(response.getRequestId(), not(emptyOrNullString()));

    SendRequestProcessData data = response.getData();
    assertThat(data.getApplicationid(), greaterThan(0));
    assertThat(data.getCitizenid(), greaterThan(0));
    assertThat(data.getApplicantid(), greaterThan(0));
    assertThat(data.getStaffid(), anyOf(nullValue(), instanceOf(Integer.class)));
    assertThat(
        data.getDateofapplication(),
        allOf(
            not(emptyOrNullString()),
            matchesPattern("^\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}\\.\\d{3}Z$")));
    assertThat(data.getKindofapplication(), not(emptyOrNullString()));
    assertThat(data.getStatusofapplication(), is("approved"));
    assertThat(data.getChannel(), not(emptyOrNullString()));
    assertThat(data.getImage(), anyOf(nullValue(), instanceOf(String.class)));
  }

  @Test
  public void requestProcessRejectedTest() {

    SendRequestProcessRequest request =
        SendRequestProcessRequest.builder()
            .staffid(sendAdminRequestForStuffId())
            .applId(getApplicationsForApplId("approved"))
            .action("rejected")
            .build();

    SendRequestProcessResponse response =
        RequestManager.postRequest(
            REQ_SPEC,
            RESP_SPEC,
            Endpoints.REQUEST_PROCESS,
            request,
            SendRequestProcessResponse.class);

    System.out.println("Response" + response);

    assertThat(response, notNullValue());
    assertThat(response.getRequestId(), not(emptyOrNullString()));

    SendRequestProcessData data = response.getData();
    assertThat(data.getApplicationid(), greaterThan(0));
    assertThat(data.getCitizenid(), greaterThan(0));
    assertThat(data.getApplicantid(), greaterThan(0));
    assertThat(data.getStaffid(), anyOf(nullValue(), instanceOf(Integer.class)));
    assertThat(
        data.getDateofapplication(),
        allOf(
            not(emptyOrNullString()),
            matchesPattern("^\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}\\.\\d{3}Z$")));
    assertThat(data.getKindofapplication(), not(emptyOrNullString()));
    assertThat(data.getStatusofapplication(), is("rejected"));
    assertThat(data.getChannel(), not(emptyOrNullString()));
    assertThat(data.getImage(), anyOf(nullValue(), instanceOf(String.class)));
  }

  public Integer sendAdminRequestForStuffId() {

    Faker faker = new Faker();
    java.util.Date birthdayDate = faker.date().birthday();
    LocalDate birthday = birthdayDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    String formatted = birthday.toString();

    String passport = faker.regexify("[A-Z0-9]{6,8}");
    String phone = faker.number().digits(11);

    SendAdminRequest request =
        SendAdminRequest.builder()
            .dateofbirth(formatted)
            .personalFirstName(faker.name().firstName())
            .personalLastName(faker.name().lastName())
            .personalMiddleName(faker.name().nameWithMiddle())
            .personalNumberOfPassport(passport)
            .personalPhoneNumber(phone)
            .build();

    SendAdminResponse response =
        RequestManager.postRequest(
            REQ_SPEC, RESP_SPEC, Endpoints.SEND_ADMIN_REQUEST, request, SendAdminResponse.class);

    return response.getData().getStaffid();
  }

  public Integer getApplicationsForApplId(final String status) {

    GetApplicationsResponse response =
        RequestManager.getRequest(
            REQ_SPEC, RESP_SPEC, Endpoints.GET_APPLICATIONS, GetApplicationsResponse.class);

    List<GetApplicationsResponseData> filtered =
        response.getData().stream()
            .filter(app -> status.equalsIgnoreCase(app.getStatusOfApplication()))
            .toList();

    Random random = new Random();
    int randomIndex = random.nextInt(filtered.size());

    return filtered.get(randomIndex).getApplicationid();
  }
}
