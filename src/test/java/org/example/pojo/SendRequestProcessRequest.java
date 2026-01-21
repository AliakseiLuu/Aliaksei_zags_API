package org.example.pojo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SendRequestProcessRequest {

  private int applId;
  private int staffid;
  private String action;
}
