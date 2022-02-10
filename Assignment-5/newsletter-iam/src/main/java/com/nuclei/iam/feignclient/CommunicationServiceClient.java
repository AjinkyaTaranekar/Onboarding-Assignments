package com.nuclei.iam.feignclient;

import com.nuclei.iam.dto.request.OtpEmailDto;
import com.nuclei.iam.dto.response.ApiResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * The interface Communication service client.
 */
@FeignClient(name = "communication-service", url = "http://localhost:8082")
public interface CommunicationServiceClient {
  
  /**
   * Send otp response entity.
   *
   * @param otpEmailDto the otp email dto
   *
   * @return the response entity
   */
  @PostMapping("/communication-service/email/sendotp")
  ResponseEntity<ApiResponseDto> sendOtp(@RequestBody OtpEmailDto otpEmailDto);
}
