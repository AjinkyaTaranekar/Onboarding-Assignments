package com.nuclei.iam.service.customerservice;

import com.nuclei.iam.constants.ResponseConstantsUtils;
import com.nuclei.iam.constants.SecurityConstantsUtils;
import com.nuclei.iam.entity.CustomerEntity;
import com.nuclei.iam.enums.CustomerRole;
import com.nuclei.iam.exceptions.AuthorizationException;
import com.nuclei.iam.exceptions.CustomerException;
import com.nuclei.iam.exceptions.ValidationException;
import com.nuclei.iam.repo.CustomerRepo;
import com.nuclei.iam.utils.GeneralUtils;
import com.nuclei.iam.validation.Validation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;


/**
 * The type Customer service.
 */
@Slf4j
@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService, UserDetailsService {
  /**
   * The constant REDIS_CACHE_VALUE.
   */
  private static final String REDIS_CACHE_VALUE = "customer";
  
  /**
   * The Utility.
   */
  private final GeneralUtils utility;
  /**
   * The Customer repo.
   */
  private final CustomerRepo customerRepo;
  /**
   * The Validation.
   */
  private final Validation validation;
  /**
   * The Bcrypt password encoder.
   */
  private BCryptPasswordEncoder bcryptPasswordEncoder;
  
  /**
   * Gets customer by email.
   *
   * @param emailId the email id
   *
   * @return the customer by email
   *
   * @throws CustomerException   the customer exception
   * @throws ValidationException the validation exception
   */
  @Override
  @CachePut(value = REDIS_CACHE_VALUE, key = "#emailId")
  public CustomerEntity getCustomerByEmail(final String emailId) throws CustomerException, ValidationException {
    validation.validateEmailId(emailId);
    return customerRepo.findByEmailId(emailId).orElseThrow(() -> new CustomerException(
        String.format(ResponseConstantsUtils.EMAIL_NOT_VERIFIED, emailId),
        HttpStatus.NOT_FOUND));
  }
  
  /**
   * Gets customer by id.
   *
   * @param id the id
   *
   * @return the customer by id
   *
   * @throws CustomerException the customer exception
   */
  @Override
  @Cacheable(value = REDIS_CACHE_VALUE, key = "#id")
  public CustomerEntity getCustomerById(final Integer id) throws CustomerException {
    return customerRepo.findById(id).orElseThrow(() -> new CustomerException(
        String.format(ResponseConstantsUtils.ID_NOT_FOUND, id),
        HttpStatus.NOT_FOUND));
  }
  
  /**
   * Create customer.
   *
   * @param customerEntity the customer entity
   *
   * @throws CustomerException the customer exception
   */
  @Override
  @CachePut(value = REDIS_CACHE_VALUE, key = "#customerEntity.getId()")
  public void createCustomer(CustomerEntity customerEntity) throws CustomerException {
    customerEntity.setPassword(bcryptPasswordEncoder.encode(customerEntity.getPassword()));
    customerEntity.setRoles(new HashSet<>(List.of(CustomerRole.GENERAL)));
    try {
      customerRepo.save(customerEntity);
    } catch (DataIntegrityViolationException exception) {
      throw new CustomerException(
          String.format(ResponseConstantsUtils.CUSTOMER_ALREADY_REGISTERED,
              customerEntity.getEmailId()), HttpStatus.NOT_ACCEPTABLE, exception);
    }
  }
  
  /**
   * Update customer by id customer entity.
   *
   * @param id             the id
   * @param customerEntity the customer entity
   *
   * @return the customer entity
   *
   * @throws CustomerException the customer exception
   */
  @Override
  @CachePut(value = REDIS_CACHE_VALUE, key = "#id", condition = "#result != null")
  public CustomerEntity updateCustomerById(Integer id, CustomerEntity customerEntity)
      throws CustomerException {
    final CustomerEntity existingCustomer = getCustomerById(id);
    if (!Objects.isNull(customerEntity.getPassword())) {
      customerEntity.setPassword(bcryptPasswordEncoder.encode(customerEntity.getPassword()));
    }
    utility.copyNotNullProperties(customerEntity, existingCustomer);
    return customerRepo.save(existingCustomer);
  }
  
  /**
   * Update customer to admin role by id.
   *
   * @param id       the id
   * @param adminKey the admin key
   *
   * @return the customer entity
   *
   * @throws CustomerException      the customer exception
   * @throws AuthorizationException the authorization exception
   */
  @Override
  @CachePut(value = REDIS_CACHE_VALUE, key = "#id", condition = "#result != null")
  public CustomerEntity updateCustomerToAdminRoleById(final Integer id, final String adminKey) throws CustomerException, AuthorizationException {
    if (!adminKey.equals(SecurityConstantsUtils.ADMIN_KEY)) {
      throw new AuthorizationException(ResponseConstantsUtils.INVALID_ADMIN_KEY, HttpStatus.NOT_ACCEPTABLE);
    }
    final CustomerEntity existingCustomer = getCustomerById(id);
    existingCustomer.getRoles().add(CustomerRole.ADMIN);
    return customerRepo.save(existingCustomer);
  }
  
  /**
   * Delete customer.
   *
   * @param id the customer id
   */
  @Override
  @CacheEvict(value = REDIS_CACHE_VALUE, key = "#id")
  public void deleteCustomerById(final Integer id) {
    customerRepo.deleteById(id);
  }
  
  /**
   * Load user by username user details.
   *
   * @param emailId the email id
   *
   * @return the user details
   */
  @Override
  public UserDetails loadUserByUsername(final String emailId) {
    CustomerEntity customerEntity;
    try {
      customerEntity = getCustomerByEmail(emailId);
    } catch (CustomerException | ValidationException exception) {
      throw new UsernameNotFoundException(String.format(
          ResponseConstantsUtils.CUSTOMER_NOT_REGISTERED, emailId), exception);
    }
    return new User(customerEntity.getEmailId(),
        customerEntity.getPassword(),
        Collections.emptyList()
    );
  }
}
