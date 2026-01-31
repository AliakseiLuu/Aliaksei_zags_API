package org.example.pojo.db;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Application {

  private Integer applicationid;
  private Integer citizenid;
  private Integer applicantid;
  private Integer staffid;
  private String dateofapplication;
  private String kindofapplication;
  private String statusofapplication;
  private String channel;

  @JsonProperty("from_draft")
  private String fromDraft;
}
