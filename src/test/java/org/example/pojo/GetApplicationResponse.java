package org.example.pojo;

import java.util.List;
import lombok.Getter;

@Getter
public class GetApplicationResponse {

  private String total;
  private List<ApplicationData> data;
  private String requestId;
}
