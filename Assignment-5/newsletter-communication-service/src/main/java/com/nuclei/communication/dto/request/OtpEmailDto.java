package com.nuclei.communication.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OtpEmailDto extends EmailDto{
  
  private Integer otp;
  
  private Integer otpExpiryTime;
}
