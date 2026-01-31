package org.example.pojo.api;

import java.util.List;
import lombok.Getter;

@Getter
public class GetApplicationsResponse {

  private String total;
  private List<GetApplicationsResponseData> data;
  private String requestId;
}
