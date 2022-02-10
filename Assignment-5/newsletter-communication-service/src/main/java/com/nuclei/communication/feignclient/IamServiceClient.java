package com.nuclei.communication.feignclient;

import com.nuclei.communication.entity.CustomerEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@FeignClient(name = "iam-service", url = "http://localhost:8081")
public interface IamServiceClient {
  
  
  @GetMapping("/iam-service/customer")
  ResponseEntity<CustomerEntity> getCustomer(HttpServletRequest request);
}
