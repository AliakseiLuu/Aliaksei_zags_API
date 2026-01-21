package org.example.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class SendRequestProcessData {

  private int applicationid;
  private int citizenid;
  private int applicantid;
  private int staffid;
  private String dateofapplication;
  private String kindofapplication;
  private String statusofapplication;
  private String channel;
  private String image;
}
