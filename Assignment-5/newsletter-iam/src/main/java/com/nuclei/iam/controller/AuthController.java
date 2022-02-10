package com.nuclei.iam.controller;

import com.nuclei.iam.constants.ResponseConstantsUtils;
import com.nuclei.iam.dto.request.CreateCustomerDto;
import com.nuclei.iam.dto.request.EmailDto;
import com.nuclei.iam.dto.request.LoginDto;
import com.nuclei.iam.dto.request.ValidateEmailWithOtpDto;
import com.nuclei.iam.dto.response.ApiResponseDto;
import com.nuclei.iam.dto.response.JwtDto;
import com.nuclei.iam.entity.CustomerEntity;
import com.nuclei.iam.exceptions.AuthorizationException;
import com.nuclei.iam.exceptions.CustomerException;
import com.nuclei.iam.exceptions.ValidationException;
import com.nuclei.iam.mapper.CustomerMapper;
import com.nuclei.iam.service.authservice.AuthService;
import com.nuclei.iam.utils.GeneralUtils;
import com.nuclei.iam.utils.JwtUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


/**
 * The type Auth controller.
 */
@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {
  
  /**
   * The Auth service.
   */
  private final AuthService authService;
  /**
   * The Customer mapper.
   */
  private final CustomerMapper customerMapper;
  
  /**
   * The General utils.
   */
  private final GeneralUtils generalUtils;
  /**
   * The Jwt utils.
   */
  private final JwtUtils jwtUtils;
  
  /**
   * Login response entity.
   *
   * @param loginDto the login dto
   *
   * @return the response entity
   *
   * @throws AuthorizationException the authorization exception
   * @throws CustomerException      the customer exception
   * @throws ValidationException    the validation exception
   */
  @PostMapping("/login")
  public ResponseEntity<ApiResponseDto> login(@RequestBody LoginDto loginDto)
      throws AuthorizationException, CustomerException, ValidationException {
    final CustomerEntity customerEntity = customerMapper.toEntity(loginDto);
    final JwtDto jwtDto = authService.login(customerEntity);
    return generalUtils.apiResponseEntityBuilder(HttpStatus.ACCEPTED,
        ResponseConstantsUtils.LOGIN_SUCCESSFUL, jwtDto);
  }
  
  /**
   * Logout response entity.
   *
   * @param request the request
   *
   * @return the response entity
   *
   * @throws AuthorizationException the authorization exception
   * @throws CustomerException      the customer exception
   * @throws ValidationException    the validation exception
   */
  @PostMapping("/logout")
  public ResponseEntity<ApiResponseDto> logout(HttpServletRequest request)
      throws AuthorizationException, CustomerException, ValidationException {
    final String token = jwtUtils.extractToken(request);
    final Integer id = jwtUtils.getIdFromToken(token);
    authService.logout(id);
    return generalUtils.apiResponseEntityBuilder(HttpStatus.ACCEPTED,
        ResponseConstantsUtils.LOGOUT_SUCCESSFUL, Boolean.TRUE);
  }
  
  /**
   * Verify email response entity.
   *
   * @param emailDto the email dto
   *
   * @return the response entity
   *
   * @throws ValidationException the validation exception
   * @throws CustomerException   the customer exception
   */
  @PostMapping("/verifyemail")
  public ResponseEntity<ApiResponseDto> verifyEmail(@RequestBody EmailDto emailDto)
      throws ValidationException, CustomerException {
    final CustomerEntity customerEntity = customerMapper.toEntity(emailDto);
    final boolean status = authService.verifyEmail(customerEntity);
    return generalUtils.apiResponseEntityBuilder(HttpStatus.OK,
        ResponseConstantsUtils.VERIFICATION_SUCCESSFUL, status);
  }
  
  /**
   * Validate email by otp response entity.
   *
   * @param validateEmailWithOtpDto the validate email with otp dto
   *
   * @return the response entity
   *
   * @throws CustomerException      the customer exception
   * @throws ValidationException    the validation exception
   * @throws AuthorizationException the authorization exception
   */
  @PostMapping("/validateemail")
  public ResponseEntity<ApiResponseDto> validateEmailByOtp(@RequestBody ValidateEmailWithOtpDto
                                                               validateEmailWithOtpDto)
      throws CustomerException, ValidationException, AuthorizationException {
    final CustomerEntity customerEntity =
        customerMapper.toEntity(validateEmailWithOtpDto);
    authService.validateEmailByOtp(customerEntity);
    return generalUtils.apiResponseEntityBuilder(HttpStatus.OK,
        ResponseConstantsUtils.VALIDATION_SUCCESSFUL, Boolean.TRUE);
  }
  
  /**
   * Signup response entity.
   *
   * @param createCustomerDto the create customer dto
   *
   * @return the response entity
   *
   * @throws ValidationException    the validation exception
   * @throws AuthorizationException the authorization exception
   * @throws CustomerException      the customer exception
   */
  @PostMapping("/signup")
  public ResponseEntity<ApiResponseDto> signup(@RequestBody CreateCustomerDto createCustomerDto)
      throws ValidationException, AuthorizationException, CustomerException {
    final CustomerEntity customerEntity = customerMapper.toEntity(createCustomerDto);
    final JwtDto jwtDto = authService.signup(customerEntity);
    return generalUtils.apiResponseEntityBuilder(HttpStatus.CREATED,
        ResponseConstantsUtils.SIGN_UP_SUCCESSFUL, jwtDto);
  }
}
