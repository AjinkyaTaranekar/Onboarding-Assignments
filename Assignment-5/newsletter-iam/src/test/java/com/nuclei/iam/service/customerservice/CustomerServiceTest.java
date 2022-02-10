package com.nuclei.iam.service.customerservice;

import com.nuclei.iam.dto.request.UpdateCustomerDto;
import com.nuclei.iam.entity.CustomerEntity;
import com.nuclei.iam.exceptions.CustomerException;
import com.nuclei.iam.exceptions.ValidationException;
import com.nuclei.iam.mapper.CustomerMapper;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.jupiter.api.Assertions.*;


/**
 * The type Customer service test.
 */
@ContextConfiguration
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CustomerServiceTest {
  
  /**
   * The Customer service.
   */
  @Autowired
  CustomerService customerService;
  
  /**
   * The Customer mapper.
   */
  @Autowired
  CustomerMapper customerMapper;
  
  
  /**
   * The Customer.
   */
  private CustomerEntity customer;
  
  /**
   * Instantiates a new Customer service test.
   */
  public CustomerServiceTest() {
    this.customer = new CustomerEntity("Ajinkya", "ajinkya@gmail.com", "Abc" +
        "@1234");
  }
  
  /**
   * Create customer.
   */
  @Test
  @Order(value = 1)
  public void createCustomerWithCorrectDetails() {
    assertDoesNotThrow(() -> customerService.createCustomer(customer));
  }
  
  /**
   * Gets customer by email.
   *
   * @throws CustomerException   the customer exception
   * @throws ValidationException the validation exception
   */
  @Test
  @Order(value = 2)
  public void getCustomerByEmail() throws CustomerException, ValidationException {
    customer =
        customerService.getCustomerByEmail(customer.getEmailId());
    assertNotNull(customer.getId());
  }
  
  /**
   * Gets customer by id.
   *
   * @throws CustomerException the customer exception
   */
  @Test
  @Order(value = 3)
  public void getCustomerById() throws CustomerException {
    customer =
        customerService.getCustomerById(customer.getId());
    assertNotNull(customer);
  }
  
  /**
   * Update customer by id.
   *
   * @throws CustomerException   the customer exception
   * @throws ValidationException the validation exception
   */
  @Test
  @Order(value = 4)
  public void updateCustomerById() throws CustomerException, ValidationException {
    UpdateCustomerDto updateCustomerDto = new UpdateCustomerDto("Ajinkya",
        "Abc@1345");
    CustomerEntity customerEntity = customerMapper.toEntity(updateCustomerDto);
    customer =
        customerService.updateCustomerById(customer.getId(), customerEntity);
    assertNotNull(customer);
    assertEquals(customerEntity.getName(), customer.getName());
  }
  
  /**
   * Delete customer.
   */
  @Test
  @Order(value = 5)
  public void deleteCustomer() {
    assertDoesNotThrow(() -> customerService.deleteCustomerById(customer.getId()));
  }
  
  /**
   * Load user by username.
   */
  public void loadUserByUsername() {
  
  }
}
