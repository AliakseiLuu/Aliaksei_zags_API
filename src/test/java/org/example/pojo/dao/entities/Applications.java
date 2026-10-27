package org.example.pojo.dao.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Applications {

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
