package com.nuclei.communication.controller;

import com.nuclei.communication.constants.ResponseConstantsUtils;
import com.nuclei.communication.dto.request.OtpEmailDto;
import com.nuclei.communication.dto.response.ApiResponseDto;
import com.nuclei.communication.entity.CustomerEntity;
import com.nuclei.communication.exceptions.AuthorizationException;
import com.nuclei.communication.exceptions.ValidationException;
import com.nuclei.communication.feignclient.IamServiceClient;
import com.nuclei.communication.service.emailservice.EmailService;
import com.nuclei.communication.utils.GeneralUtils;

import javax.servlet.http.HttpServletRequest;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 * The type Customer controller.
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/email")
@AllArgsConstructor
public class EmailController {
  
  private final EmailService emailService;
  private final GeneralUtils generalUtils;
  private final IamServiceClient iamServiceClient;
  
  /**
   * Gets customer.
   *
   * @param request the request
   *
   * @return the customer
   *
   * @throws ValidationException    the validation exception
   */
  @PostMapping("/sendotp")
  public ResponseEntity<ApiResponseDto> sendOtp(HttpServletRequest request,
                                                @RequestBody OtpEmailDto otpEmailDto)
      throws ValidationException, AuthorizationException {
    //TODO (AjinkyaTaranekar): fix bug while mapping entity to enable
    // authorization
//    ResponseEntity<CustomerEntity> responseEntity = iamServiceClient.getCustomer(request);
//    if(responseEntity.getStatusCode() != HttpStatus.OK) {
//      throw new AuthorizationException(ResponseConstantsUtils.INVALID_TOKEN,
//          HttpStatus.NOT_ACCEPTABLE);
//    }
    emailService.sendOtp(otpEmailDto);
  
    return generalUtils.apiResponseEntityBuilder(HttpStatus.OK,
        ResponseConstantsUtils.OTP_SENT_SUCCESSFUL, Boolean.TRUE);
  }
  
  
}