package org.example.tests;

import java.time.LocalDate;
import java.time.ZoneId;
import net.datafaker.Faker;
import org.assertj.core.api.SoftAssertions;
import org.example.pojo.api.SendUserRequestRequest;
import org.example.pojo.api.SendUserRequestResponse;
import org.example.pojo.api.SendUserRequsetResponseData;
import org.example.pojo.db.Application;
import org.example.utils.Endpoints;
import org.example.utils.RequestManager;
import org.example.utils.db.ApplicationsTable;
import org.junit.jupiter.api.Test;

public class SendUserRequestTest extends BaseTest {

  @Test
  public void sendUserRequestMarriageTest() {

    Faker faker = new Faker();
    java.util.Date birthdayDate = faker.date().birthday();
    LocalDate birthday = birthdayDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    String formatted = birthday.toString();

    String passport = faker.regexify("[A-Z0-9]{6,8}");
    String phone = faker.number().digits(11);

    SendUserRequestRequest request =
        SendUserRequestRequest.builder()
            .anotherPersonFirstName(faker.name().firstName())
            .anotherPersonLastName(faker.name().lastName())
            .anotherPersonMiddleName(faker.name().malefirstName())
            .anotherPersonPassport(passport)
            .birthOfAnotoherPerson(formatted)
            .citizenAddress(faker.address().streetAddress())
            .citizenBirthDate(formatted)
            .citizenFirstName(faker.name().firstName())
            .citizenGender(faker.demographic().sex())
            .citizenLastName(faker.name().lastName())
            .citizenMiddleName(faker.name().femaleFirstName())
            .citizenNumberOfPassport(passport)
            .dateOfMarriage(formatted)
            .mode("wedding")
            .newLastName(faker.name().lastName())
            .personalAddress(faker.address().streetAddress())
            .personalFirstName(faker.name().firstName())
            .personalLastName(faker.name().lastName())
            .personalMiddleName(faker.funnyName().name())
            .personalNumberOfPassport(passport)
            .personalPhoneNumber(phone)
            .build();

    SendUserRequestResponse response =
        RequestManager.postRequest(
            REQ_SPEC,
            RESP_SPEC,
            Endpoints.SEND_USER_REQUEST,
            request,
            SendUserRequestResponse.class);
    System.out.println("answer" + response);

    SoftAssertions softAssertions = new SoftAssertions();

    softAssertions.assertThat(response).as("Response object").isNotNull();
    softAssertions.assertThat(response.getRequestId()).as("Request ID").isNotEmpty();

    SendUserRequsetResponseData data = response.getData();
    softAssertions.assertThat(data.getApplicationid()).as("Application ID").isGreaterThan(0);
    softAssertions.assertThat(data.getCitizenid()).as("Citizen ID").isGreaterThan(0);
    softAssertions.assertThat(data.getApplicantid()).as("Applicant ID").isGreaterThan(0);
    softAssertions
        .assertThat(data.getMerrigecertificateid())
        .as("Marriage certificate ID")
        .isGreaterThan(0);
    softAssertions.assertAll();

    Integer applicationId = response.getData().getApplicationid();
    Application dbApplication = ApplicationsTable.getApplicationById(applicationId);

    softAssertions
        .assertThat(dbApplication.getApplicationid())
        .as("Created via API application exist in DB")
        .isEqualTo(applicationId);
    softAssertions
        .assertThat(dbApplication.getStatusofapplication())
        .as("Created via API application has 'under consideration' status")
        .isEqualTo("under consideration");
    softAssertions.assertAll();
  }
}
