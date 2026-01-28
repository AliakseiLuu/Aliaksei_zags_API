package org.example.pojo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SendUserRequestRequest {

  private String anotherPersonFirstName;
  private String anotherPersonLastName;
  private String anotherPersonMiddleName;
  private String anotherPersonPassport;
  private String birthFather;
  private String birthGrandma;
  private String birthGrandpa;
  private String birthMother;
  private String birthOfAnotoherPerson;
  private String birthPlace;
  private String citizenAddress;
  private String citizenBirthDate;
  private String citizenFirstName;
  private String citizenGender;
  private String citizenLastName;
  private String citizenMiddleName;
  private String citizenNumberOfPassport;
  private String dateOfMarriage;
  private String deathDateOfDeath;
  private String deathPlaceOfDeath;
  private String mode;
  private String newLastName;
  private String personalAddress;
  private String personalFirstName;
  private String personalLastName;
  private String personalMiddleName;
  private String personalNumberOfPassport;
  private String personalPhoneNumber;
}
