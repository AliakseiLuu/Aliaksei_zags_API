package org.example.pojo.dao.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Staff {

  private Integer staffid;
  private String surname;
  private String name;
  private String middlename;
  private String dateofbirth;
  private String passportnumber;
  private String phonenumber;
}
