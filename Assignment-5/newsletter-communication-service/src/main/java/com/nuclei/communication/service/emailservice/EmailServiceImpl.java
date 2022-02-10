package com.nuclei.communication.service.emailservice;

import com.nuclei.communication.constants.KafkaConstantsUtils;
import com.nuclei.communication.constants.StringConstantsUtils;
import com.nuclei.communication.dto.request.EmailDto;
import com.nuclei.communication.dto.request.OtpEmailDto;
import com.nuclei.communication.exceptions.ValidationException;
import com.nuclei.communication.validation.Validation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class EmailServiceImpl implements EmailService {
  
  private final Validation validation;
  
  @Autowired
  private final KafkaTemplate<Integer, EmailDto> kafkaTemplate;
  
  @Override
  public void sendOtp(final OtpEmailDto otpEmailDto) throws ValidationException {
    validateOtpEmailDto(otpEmailDto);
    EmailDto emailDto = new EmailDto();
    emailDto.setEmailId(otpEmailDto.getEmailId());
    emailDto.setName(otpEmailDto.getName());
    emailDto.setSubject(String.format(StringConstantsUtils.OTP_SUBJECT,
        otpEmailDto.getName()));
    emailDto.setBody(String.format(StringConstantsUtils.OTP_BODY,
        otpEmailDto.getName(), otpEmailDto.getEmailId(),
        otpEmailDto.getOtp(), otpEmailDto.getOtpExpiryTime()/60));
    kafkaTemplate.send(KafkaConstantsUtils.KAFKA_OTP_TOPIC_NAME, emailDto);
  }
  
  void validateOtpEmailDto(OtpEmailDto otpEmailDto) throws ValidationException {
    validation.validateEmailId(otpEmailDto.getEmailId());
    validation.validateName(otpEmailDto.getName());
  }
}
