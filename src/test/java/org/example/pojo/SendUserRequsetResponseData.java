package org.example.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class SendUserRequsetResponseData {

  private int applicantid;
  private int applicationid;
  private int citizenid;
  private int merrigecertificateid;
}
