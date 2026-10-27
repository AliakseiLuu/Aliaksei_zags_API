package org.example.pojo.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class SendAdminResponse {

  private SendAdminResponseData data;
  private String requestId;
}
