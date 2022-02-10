package com.nuclei.iam.service.authservice;

import com.nuclei.iam.constants.ResponseConstantsUtils;
import com.nuclei.iam.constants.SecurityConstantsUtils;
import com.nuclei.iam.dto.request.OtpEmailDto;
import com.nuclei.iam.dto.response.JwtDto;
import com.nuclei.iam.entity.CustomerEntity;
import com.nuclei.iam.exceptions.AuthorizationException;
import com.nuclei.iam.exceptions.CustomerException;
import com.nuclei.iam.exceptions.ValidationException;
import com.nuclei.iam.feignclient.CommunicationServiceClient;
import com.nuclei.iam.service.customerservice.CustomerService;
import com.nuclei.iam.utils.GeneralUtils;
import com.nuclei.iam.utils.JwtUtils;
import com.nuclei.iam.validation.Validation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;

/**
 * The type Auth service.
 */
@Service
@Slf4j
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {
  
  /**
   * The Customer service.
   */
  private final CustomerService customerService;
  /**
   * The Jwt utils.
   */
  private final JwtUtils jwtUtils;
  
  /**
   * The General utils.
   */
  private final GeneralUtils generalUtils;
  
  /**
   * The Authentication manager.
   */
  private final AuthenticationManager authenticationManager;
  /**
   * The Validation.
   */
  private final Validation validation;
  
  /**
   * The Communication service client.
   */
  private final CommunicationServiceClient communicationServiceClient;
  
  /**
   * The Bcrypt password encoder.
   */
  private BCryptPasswordEncoder bcryptPasswordEncoder;
  
  /**
   * Login jwt dto.
   *
   * @param customerEntity the customer entity
   *
   * @return the jwt dto
   *
   * @throws AuthorizationException the authorization exception
   * @throws CustomerException      the customer exception
   * @throws ValidationException    the validation exception
   */
  @Override
  public JwtDto login(final CustomerEntity customerEntity)
      throws AuthorizationException, CustomerException, ValidationException {
    final CustomerEntity customerEntityFromDb =
        customerService.getCustomerByEmail(customerEntity.getEmailId());
    try {
      authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
          customerEntity.getEmailId(), customerEntity.getPassword()));
      setLastLogin(customerEntity);
      final CustomerEntity updateCustomerById =
          customerService.updateCustomerById(customerEntityFromDb.getId(),
              customerEntity);
      final String jwt = jwtUtils.generateToken(updateCustomerById);
      return new JwtDto(jwt);
    } catch (Exception exception) {
      log.error(ResponseConstantsUtils.INVALID_CREDENTIALS, exception);
      throw new AuthorizationException(ResponseConstantsUtils.INVALID_CREDENTIALS,
          HttpStatus.UNAUTHORIZED, exception);
    }
  }
  
  /**
   * Logout.
   *
   * @param id the id
   *
   * @throws CustomerException   the customer exception
   * @throws ValidationException the validation exception
   */
  @Override
  public void logout(final Integer id) throws
      CustomerException, ValidationException {
    final CustomerEntity customerEntityFromDb =
        customerService.getCustomerById(id);
    customerEntityFromDb.setIsLoggedIn(Boolean.FALSE);
    customerService.updateCustomerById(customerEntityFromDb.getId(),
        customerEntityFromDb);
  }
  
  /**
   * Signup jwt dto.
   *
   * @param customerEntity the customer entity
   *
   * @return the jwt dto
   *
   * @throws ValidationException    the validation exception
   * @throws CustomerException      the customer exception
   * @throws AuthorizationException the authorization exception
   */
  @Override
  public JwtDto signup(final CustomerEntity customerEntity) throws ValidationException,
      CustomerException, AuthorizationException {
    validateCustomer(customerEntity);
    final CustomerEntity customerEntityFromDb =
        customerService.getCustomerByEmail(customerEntity.getEmailId());
    if (customerEntityFromDb.getIsSignedUp()) {
      throw new AuthorizationException(String.format(
          ResponseConstantsUtils.CUSTOMER_ALREADY_REGISTERED,
          customerEntity.getEmailId()), HttpStatus.UNAUTHORIZED);
    }
    setLastLogin(customerEntity);
    customerEntity.setIsSignedUp(Boolean.TRUE);
    final CustomerEntity savedCustomerEntity =
        customerService.updateCustomerById(customerEntityFromDb.getId(),
            customerEntity);
    return new JwtDto(jwtUtils.generateToken(savedCustomerEntity));
  }
  
  /**
   * Validate customer.
   *
   * @param customerEntity the customer entity
   *
   * @throws ValidationException the validation exception
   */
  private void validateCustomer(CustomerEntity customerEntity) throws ValidationException {
    validation.validateName(customerEntity.getName());
    validation.validatePassword(customerEntity.getPassword());
  }
  
  /**
   * Verify email boolean.
   *
   * @param customerEntity the customer entity
   *
   * @return the boolean
   *
   * @throws ValidationException the validation exception
   * @throws CustomerException   the customer exception
   */
  @Override
  public boolean verifyEmail(final CustomerEntity customerEntity)
      throws ValidationException, CustomerException {
    try {
      final CustomerEntity customerEntityFromDb =
          customerService.getCustomerByEmail(customerEntity.getEmailId());
      if (Objects.isNull(customerEntityFromDb.getIsSignedUp())) {
        throw new CustomerException(String.format(ResponseConstantsUtils.EMAIL_NOT_VERIFIED
            , customerEntityFromDb.getEmailId()), HttpStatus.UNAUTHORIZED);
      }
      return true;
    } catch (CustomerException exception) {
      if (exception.getStatusCode() != HttpStatus.BAD_REQUEST) {
        createADummyCustomer(customerEntity);
        return false;
      }
      throw exception;
    }
  }
  
  /**
   * Validate email by otp boolean.
   *
   * @param customerEntity the customer entity
   *
   * @throws CustomerException      the customer exception
   * @throws ValidationException    the validation exception
   * @throws AuthorizationException the authorization exception
   */
  @Override
  public void validateEmailByOtp(final CustomerEntity customerEntity)
      throws CustomerException, ValidationException, AuthorizationException {
    final CustomerEntity customerEntityFromDb =
        customerService.getCustomerByEmail(customerEntity.getEmailId());
    if (customerEntityFromDb.getOtpExpiryTime().before(new Date())) {
      throw new AuthorizationException(String.format(ResponseConstantsUtils.OTP_EXPIRED,
          customerEntity.getOtp()), HttpStatus.NOT_ACCEPTABLE);
    }
    if (!bcryptPasswordEncoder.matches(customerEntity.getOtp(),
        customerEntityFromDb.getOtp())) {
      customerService.deleteCustomerById(customerEntityFromDb.getId());
      throw new AuthorizationException(String.format(ResponseConstantsUtils.INVALID_OTP,
          customerEntity.getOtp()), HttpStatus.NOT_ACCEPTABLE);
    }
  }
  
  /**
   * Sets last login.
   *
   * @param customerEntity the customer entity
   */
  private void setLastLogin(CustomerEntity customerEntity) {
    customerEntity.setLastLogin(new Date());
    customerEntity.setIsLoggedIn(true);
  }
  
  /**
   * Create a dummy customer.
   *
   * @param customerEntity the customer entity
   *
   * @throws CustomerException   the customer exception
   * @throws ValidationException the validation exception
   */
  private void createADummyCustomer(CustomerEntity customerEntity)
      throws CustomerException, ValidationException {
    final Integer otp = generalUtils.generateOtp();
    final String password = generalUtils.generateRawPassword();
    sendOtp(customerEntity.getEmailId(), otp);
    customerEntity.setOtp(bcryptPasswordEncoder.encode(otp.toString()));
    customerEntity.setOtpExpiryTime(DateUtils.addSeconds(new Date(),
        SecurityConstantsUtils.OTP_EXPIRATION_TIME));
    customerEntity.setPassword(password);
    customerEntity.setIsSignedUp(false);
    customerService.createCustomer(customerEntity);
  }
  
  /**
   * Send otp.
   *
   * @param emailId the email id
   * @param otp     the otp
   */
  private void sendOtp(String emailId, Integer otp) {
    final OtpEmailDto otpEmailDto = new OtpEmailDto();
    otpEmailDto.setOtp(otp);
    otpEmailDto.setEmailId(emailId);
    otpEmailDto.setName("Customer");
    otpEmailDto.setOtpExpiryTime(SecurityConstantsUtils.OTP_EXPIRATION_TIME);
    communicationServiceClient.sendOtp(otpEmailDto);
  }
}
