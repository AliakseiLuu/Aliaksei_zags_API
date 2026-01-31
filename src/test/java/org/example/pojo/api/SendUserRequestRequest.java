package org.example.pojo.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SendUserRequestRequest {

  private String anotherPersonFirstName;
  private String anotherPersonLastName;
  private String anotherPersonMiddleName;
  private String anotherPersonPassport;

  @JsonProperty("birth_father")
  private String birthFather;

  @JsonProperty("birth_grandma")
  private String birthGrandma;

  @JsonProperty("birth_grandpa")
  private String birthGrandpa;

  @JsonProperty("birth_mother")
  private String birthMother;

  @JsonProperty("birth_of_anotoherPerson")
  private String birthOfAnotoherPerson;

  @JsonProperty("birth_place")
  private String birthPlace;

  private String citizenAddress;
  private String citizenBirthDate;
  private String citizenFirstName;
  private String citizenGender;
  private String citizenLastName;
  private String citizenMiddleName;
  private String citizenNumberOfPassport;
  private String dateOfMarriage;

  @JsonProperty("death_dateOfDeath")
  private String deathDateOfDeath;

  @JsonProperty("death_placeOfDeath")
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
