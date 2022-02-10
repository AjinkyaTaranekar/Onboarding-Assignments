package com.nuclei.iam.mapper;

import com.nuclei.iam.dto.request.*;
import com.nuclei.iam.dto.response.CustomerDto;
import com.nuclei.iam.entity.CustomerEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * The interface Customer mapper.
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CustomerMapper {
  
  /**
   * To entity customer entity.
   *
   * @param emailDto the email dto
   *
   * @return the customer entity
   */
  CustomerEntity toEntity(EmailDto emailDto);
  
  /**
   * To entity customer entity.
   *
   * @param updateCustomerDto the update customer dto
   *
   * @return the customer entity
   */
  CustomerEntity toEntity(UpdateCustomerDto updateCustomerDto);
  
  /**
   * To entity customer entity.
   *
   * @param loginDto the login dto
   *
   * @return the customer entity
   */
  CustomerEntity toEntity(LoginDto loginDto);
  
  /**
   * To entity customer entity.
   *
   * @param createCustomerDto the create customer dto
   *
   * @return the customer entity
   */
  CustomerEntity toEntity(CreateCustomerDto createCustomerDto);
  
  /**
   * To entity customer entity.
   *
   * @param validateEmailWithOtpDto the validate email with otp dto
   *
   * @return the customer entity
   */
  CustomerEntity toEntity(ValidateEmailWithOtpDto validateEmailWithOtpDto);
  
  /**
   * To dto customer dto.
   *
   * @param customerEntity the customer entity
   *
   * @return the customer dto
   */
  CustomerDto toDto(CustomerEntity customerEntity);
}
