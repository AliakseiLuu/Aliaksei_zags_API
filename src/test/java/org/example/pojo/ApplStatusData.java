package org.example.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApplStatusData {

  private String dateofapplication;
  private String kindofapplication;
  private String statusofapplication;
}
