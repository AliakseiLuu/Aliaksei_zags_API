package org.example.tests;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import net.datafaker.Faker;
import org.assertj.core.api.SoftAssertions;
import org.example.pojo.api.SendAdminRequest;
import org.example.pojo.api.SendAdminResponse;
import org.example.pojo.dao.StaffDao;
import org.example.pojo.dao.entities.Staff;
import org.example.pojo.dao.impl.StaffDaoImpl;
import org.example.utils.Endpoints;
import org.example.utils.RequestManager;
import org.example.utils.db.ConnectionPool;
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
    System.out.println("answer: " + response.getData().getStaffid());
    Integer staffId = response.getData().getStaffid();

    SoftAssertions softAssertions = new SoftAssertions();
    Staff dbStaff;
    try (Connection conn = ConnectionPool.getConnection()) {
      StaffDao staffDao = new StaffDaoImpl(conn);
      dbStaff = staffDao.findById(staffId).orElse(null);

    } catch (SQLException e) {
      throw new RuntimeException(e);
    }

    softAssertions.assertThat(dbStaff.getStaffid()).isEqualTo(response.getData().getStaffid());
    softAssertions.assertThat(dbStaff.getDateofbirth()).isEqualTo(request.getDateofbirth());
    softAssertions.assertThat(dbStaff.getName()).isEqualTo(request.getPersonalFirstName());
    softAssertions.assertThat(dbStaff.getPhonenumber()).isEqualTo(request.getPersonalPhoneNumber());
    softAssertions.assertThat(dbStaff.getSurname()).isEqualTo(request.getPersonalLastName());
    softAssertions.assertThat(dbStaff.getMiddlename()).isEqualTo(request.getPersonalMiddleName());
    softAssertions
        .assertThat(dbStaff.getPassportnumber())
        .isEqualTo(request.getPersonalNumberOfPassport());
    softAssertions.assertAll();
  }

  @Test
  public void deleteAdminFromDBTest() {

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
    System.out.println("answer: " + response.getData().getStaffid());
    Integer staffId = response.getData().getStaffid();

    SoftAssertions softAssertions = new SoftAssertions();
    Staff dbStaff;
    try (Connection conn = ConnectionPool.getConnection()) {
      StaffDao staffDao = new StaffDaoImpl(conn);
      dbStaff = staffDao.findById(staffId).orElse(null);

    } catch (SQLException e) {
      throw new RuntimeException(e);
    }

    softAssertions.assertThat(dbStaff).as("Created via API staff exists in DB").isNotNull();
    softAssertions.assertAll();

    try (Connection conn = ConnectionPool.getConnection()) {
      StaffDao staffDao = new StaffDaoImpl(conn);
      staffDao.deleteById(staffId);

    } catch (SQLException e) {
      throw new RuntimeException(e);
    }

    Staff staffAfterDelete;
    try (Connection conn = ConnectionPool.getConnection()) {
      StaffDao staffDao = new StaffDaoImpl(conn);
      staffAfterDelete = staffDao.findById(staffId).orElse(null);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }

    softAssertions
        .assertThat(staffAfterDelete)
        .as("Created via API staff deleted from DB")
        .isNull();
    softAssertions.assertAll();
  }
}
