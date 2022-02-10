package com.nuclei.iam.service.customerservice;

import com.nuclei.iam.entity.CustomerEntity;
import com.nuclei.iam.exceptions.AuthorizationException;
import com.nuclei.iam.exceptions.CustomerException;
import com.nuclei.iam.exceptions.ValidationException;

/**
 * The interface Customer service.
 */
public interface CustomerService {
  
  /**
   * Gets customer by email.
   *
   * @param email the email
   *
   * @return the customer by email
   *
   * @throws CustomerException   the customer exception
   * @throws ValidationException the validation exception
   */
  CustomerEntity getCustomerByEmail(String email) throws CustomerException, ValidationException;
  
  /**
   * Gets customer by id.
   *
   * @param id the id
   *
   * @return the customer by id
   *
   * @throws CustomerException the customer exception
   */
  CustomerEntity getCustomerById(Integer id) throws CustomerException;
  
  /**
   * Create customer.
   *
   * @param customerEntity the customer entity
   *
   * @throws ValidationException the validation exception
   * @throws CustomerException   the customer exception
   */
  void createCustomer(CustomerEntity customerEntity) throws ValidationException, CustomerException;
  
  /**
   * Update customer by id customer entity.
   *
   * @param id             the id
   * @param customerEntity the customer entity
   *
   * @return the customer entity
   *
   * @throws ValidationException the validation exception
   * @throws CustomerException   the customer exception
   */
  CustomerEntity updateCustomerById(Integer id,
                                    CustomerEntity customerEntity)
      throws ValidationException, CustomerException;
  
  /**
   * Update customer to admin role by id.
   *
   * @param id       the id
   * @param adminKey the admin key
   *
   * @return the customer entity
   *
   * @throws ValidationException    the validation exception
   * @throws CustomerException      the customer exception
   * @throws AuthorizationException the authorization exception
   */
  CustomerEntity updateCustomerToAdminRoleById(Integer id, String adminKey)
      throws ValidationException, CustomerException, AuthorizationException;
  
  /**
   * Delete customer.
   *
   * @param id the customer id
   *
   * @throws ValidationException the validation exception
   * @throws CustomerException   the customer exception
   */
  void deleteCustomerById(Integer id) throws ValidationException,
      CustomerException;
}
