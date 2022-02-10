package com.nuclei.communication.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotificationEmailDto extends EmailDto{
  
  private String notification;
}
