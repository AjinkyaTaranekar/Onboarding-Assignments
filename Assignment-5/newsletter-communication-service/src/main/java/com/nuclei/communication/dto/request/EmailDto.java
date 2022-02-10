package com.nuclei.communication.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * The type Email dto.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmailDto implements Serializable {
  
  private String emailId;
  private String name;
  
  private String body;
  private String subject;
  
  @Override
  public String toString() {
    return "EmailDto{" +
        "emailId='" + emailId + '\'' +
        ", name='" + name + '\'' +
        ", body='" + body + '\'' +
        ", subject='" + subject + '\'' +
        '}';
  }
}
