package com.nuclei.iam.service.authservice;

import com.nuclei.iam.dto.response.JwtDto;
import com.nuclei.iam.entity.CustomerEntity;
import com.nuclei.iam.exceptions.AuthorizationException;
import com.nuclei.iam.exceptions.CustomerException;
import com.nuclei.iam.exceptions.ValidationException;

/**
 * The interface Auth service.
 */
public interface AuthService {
  
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
  JwtDto login(CustomerEntity customerEntity)
      throws AuthorizationException, CustomerException, ValidationException;
  
  /**
   * Logout.
   *
   * @param id the id
   *
   * @throws CustomerException   the customer exception
   * @throws ValidationException the validation exception
   */
  void logout(Integer id) throws CustomerException, ValidationException;
  
  /**
   * Signup jwt dto.
   *
   * @param customerEntity the customer entity
   *
   * @return the jwt dto
   *
   * @throws ValidationException    the validation exception
   * @throws AuthorizationException the authorization exception
   * @throws CustomerException      the customer exception
   */
  JwtDto signup(CustomerEntity customerEntity)
      throws ValidationException, AuthorizationException, CustomerException;
  
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
  boolean verifyEmail(CustomerEntity customerEntity) throws ValidationException, CustomerException;
  
  /**
   * Validate email by otp boolean.
   *
   * @param customerEntity the customer entity
   *
   * @throws CustomerException      the customer exception
   * @throws ValidationException    the validation exception
   * @throws AuthorizationException the authorization exception
   */
  void validateEmailByOtp(CustomerEntity customerEntity)
      throws CustomerException, ValidationException, AuthorizationException;
}
