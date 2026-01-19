package org.example.tests;

import java.time.LocalDate;
import java.time.ZoneId;
import net.datafaker.Faker;
import org.example.pojo.SendAdminRequest;
import org.example.pojo.SendAdminResponse;
import org.example.utils.RequestManager;
import org.junit.jupiter.api.Test;

public class SendAdminRequestAPI extends BaseTest {

  @Test
  public void sendAdminRequestTest() {

    Faker faker = new Faker();
    java.util.Date birthdayDate = faker.date().birthday();
    LocalDate birthday = birthdayDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    String formatted = birthday.toString();

    SendAdminRequest request = new SendAdminRequest();

    request.setDateofbirth(formatted);
    request.setPersonalFirstName(faker.name().firstName());
    request.setPersonalLastName(faker.name().lastName());
    request.setPersonalMiddleName(faker.name().nameWithMiddle());

    String passport = faker.regexify("[A-Z0-9]{6,8}");
    request.setPersonalNumberOfPassport(passport);

    String phone = faker.number().digits(11);
    request.setPersonalPhoneNumber(phone);

    SendAdminResponse response =
        RequestManager.postRequest(
            REQ_SPEC, RESP_SPEC, "sendAdminRequest", request, SendAdminResponse.class);
    System.out.println("answer" + response);
  }
}
