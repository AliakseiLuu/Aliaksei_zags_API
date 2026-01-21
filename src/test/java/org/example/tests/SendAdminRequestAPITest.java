package org.example.tests;

import java.time.LocalDate;
import java.time.ZoneId;
import net.datafaker.Faker;
import org.example.pojo.SendAdminRequest;
import org.example.pojo.SendAdminResponse;
import org.example.utils.Endpoints;
import org.example.utils.RequestManager;
import org.junit.jupiter.api.Test;

public class SendAdminRequestAPITest extends BaseTest {

  @Test
  public void sendAdminRequestTest() {

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
    System.out.println("answer" + response);
  }
}
