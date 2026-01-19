package org.example.pojo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SendAdminRequest {

  private String dateofbirth;
  private String personalFirstName;
  private String personalLastName;
  private String personalMiddleName;
  private String personalNumberOfPassport;
  private String personalPhoneNumber;
}
