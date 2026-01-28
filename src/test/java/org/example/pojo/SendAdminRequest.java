package org.example.pojo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SendAdminRequest {

  private String dateofbirth;
  private String personalFirstName;
  private String personalLastName;
  private String personalMiddleName;
  private String personalNumberOfPassport;
  private String personalPhoneNumber;
}
