package com.nuclei.iam.controller;

import com.nuclei.iam.constants.ResponseConstantsUtils;
import com.nuclei.iam.dto.request.UpdateCustomerDto;
import com.nuclei.iam.dto.response.ApiResponseDto;
import com.nuclei.iam.entity.CustomerEntity;
import com.nuclei.iam.exceptions.AuthorizationException;
import com.nuclei.iam.exceptions.CustomerException;
import com.nuclei.iam.exceptions.ValidationException;
import com.nuclei.iam.mapper.CustomerMapper;
import com.nuclei.iam.service.customerservice.CustomerService;
import com.nuclei.iam.utils.GeneralUtils;
import com.nuclei.iam.utils.JwtUtils;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


/**
 * The type Customer controller.
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/customer")
@AllArgsConstructor
public class CustomerController {
  /**
   * The Customer service.
   */
  private final CustomerService customerService;
  /**
   * The Customer mapper.
   */
  private final CustomerMapper customerMapper;
  /**
   * The Jwt utils.
   */
  private final JwtUtils jwtUtils;
  /**
   * The General utils.
   */
  private final GeneralUtils generalUtils;
  
  /**
   * Gets customer.
   *
   * @param request the request
   *
   * @return the customer
   *
   * @throws ValidationException    the validation exception
   * @throws CustomerException      the customer exception
   * @throws AuthorizationException the authorization exception
   */
  @GetMapping()
  public ResponseEntity<ApiResponseDto> getCustomer(HttpServletRequest request)
      throws ValidationException, CustomerException, AuthorizationException {
    final String token = jwtUtils.extractToken(request);
    final Integer id = jwtUtils.getIdFromToken(token);
    final CustomerEntity customerEntity = customerService.getCustomerById(id);
    
    return generalUtils.apiResponseEntityBuilder(HttpStatus.OK,
        ResponseConstantsUtils.CUSTOMER_PROFILE_FETCHED, customerMapper.toDto(customerEntity));
  }
  
  /**
   * Update customer by id response entity.
   *
   * @param request           the request
   * @param updateCustomerDto the update customer dto
   *
   * @return the response entity
   *
   * @throws CustomerException      the customer exception
   * @throws ValidationException    the validation exception
   * @throws AuthorizationException the authorization exception
   */
  @PatchMapping()
  public ResponseEntity<ApiResponseDto> updateCustomerById(
      HttpServletRequest request, @RequestBody UpdateCustomerDto updateCustomerDto)
      throws CustomerException, ValidationException, AuthorizationException {
    CustomerEntity customerEntity =
        customerMapper.toEntity(updateCustomerDto);
    final String token = jwtUtils.extractToken(request);
    final Integer id = jwtUtils.getIdFromToken(token);
    customerEntity = customerService.updateCustomerById(id, customerEntity);
    return generalUtils.apiResponseEntityBuilder(HttpStatus.OK,
        ResponseConstantsUtils.CUSTOMER_PROFILE_UPDATED, customerMapper.toDto(customerEntity));
  }
  
  /**
   * Update customer to admin role by id response entity.
   *
   * @param request the request
   * @param key     the key
   *
   * @return the response entity
   *
   * @throws CustomerException      the customer exception
   * @throws ValidationException    the validation exception
   * @throws AuthorizationException the authorization exception
   */
  @PatchMapping("/adminaccess")
  public ResponseEntity<ApiResponseDto> updateCustomerToAdminRoleById(
      HttpServletRequest request, @RequestParam String key)
      throws CustomerException, ValidationException, AuthorizationException {
    final String token = jwtUtils.extractToken(request);
    final Integer id = jwtUtils.getIdFromToken(token);
    CustomerEntity customerEntity =
        customerService.updateCustomerToAdminRoleById(id, key);
    return generalUtils.apiResponseEntityBuilder(HttpStatus.OK,
        ResponseConstantsUtils.CUSTOMER_PROFILE_UPDATED, customerMapper.toDto(customerEntity));
  }
  
  
  
}