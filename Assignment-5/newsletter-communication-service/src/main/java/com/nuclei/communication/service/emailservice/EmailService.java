package com.nuclei.communication.service.emailservice;

import com.nuclei.communication.dto.request.OtpEmailDto;
import com.nuclei.communication.exceptions.ValidationException;

public interface EmailService {
  
  void sendOtp(OtpEmailDto otpEmailDto) throws ValidationException;
  
}
