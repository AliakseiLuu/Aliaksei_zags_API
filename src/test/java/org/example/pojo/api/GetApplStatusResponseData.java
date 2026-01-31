package org.example.pojo.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetApplStatusResponseData {

  private String dateofapplication;
  private String kindofapplication;
  private String statusofapplication;
}
