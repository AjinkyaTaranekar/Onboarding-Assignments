package com.nuclei.iam.controller;

import com.nuclei.iam.dto.request.CreateCustomerDto;
import com.nuclei.iam.dto.request.LoginDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.time.Duration;

/**
 * The type Auth controller test.
 */
@AutoConfigureWebTestClient
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AuthControllerTest {
  
  /**
   * The Web test client.
   */
  @Autowired
  private WebTestClient webTestClient;
  
  /**
   * Sets up.
   */
  @BeforeEach
  public void setUp() {
    webTestClient = webTestClient
        .mutate()
        .responseTimeout(Duration.ofMillis(600000))
        .build();
  }
  
  /**
   * Login with a registered email.
   */
  @Test
  @Order(1)
  void loginWithARegisteredEmail() {
    String uri = "/auth/login";
    LoginDto loginDto = new LoginDto("ajinkyataranekar@gmail.com", "Abc@1234");
    webTestClient
        .post().uri(uri)
        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
        .body(Mono.just(loginDto), LoginDto.class)
        .exchange()
        .expectStatus().isAccepted()
        .expectHeader().contentType(MediaType.APPLICATION_JSON)
        .expectBody().jsonPath("$.body.jwt").isNotEmpty();
  }
  
  /**
   * Login with unknown email.
   */
  @Test
  @Order(2)
  void loginWithUnknownEmail() {
    String uri = "/auth/login";
    LoginDto loginDto = new LoginDto("ajinkya@gmail.com", "Abc@1234");
    webTestClient
        .post().uri(uri)
        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
        .body(Mono.just(loginDto), LoginDto.class)
        .exchange()
        .expectStatus().isNotFound()
        .expectHeader().contentType(MediaType.APPLICATION_JSON);
  }
  
  /**
   * Login with incorrect email.
   */
  @Test
  @Order(3)
  void loginWithIncorrectEmail() {
    String uri = "/auth/login";
    LoginDto loginDto = new LoginDto("ajinkyagmail.com", "Abc@1234");
    webTestClient
        .post().uri(uri)
        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
        .body(Mono.just(loginDto), LoginDto.class)
        .exchange()
        .expectStatus().isBadRequest()
        .expectHeader().contentType(MediaType.APPLICATION_JSON);
  }
  
  /**
   * Login with invalid credentials.
   */
  @Test
  @Order(3)
  void loginWithInvalidCredentials() {
    String uri = "/auth/login";
    LoginDto loginDto = new LoginDto("ajinkyataranekar@gmail.com", "Abc01234");
    webTestClient
        .post().uri(uri)
        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
        .body(Mono.just(loginDto), LoginDto.class)
        .exchange()
        .expectStatus().isUnauthorized()
        .expectHeader().contentType(MediaType.APPLICATION_JSON);
  }
  
  /**
   * Signup with incorrect email.
   */
  @Test
  @Order(4)
  void signupWithIncorrectEmail() {
    String uri = "/auth/signup";
    CreateCustomerDto createCustomerDto = new CreateCustomerDto("Ajinkya",
        "ajinkyataranekargmail.com", "Abc@1234");
    webTestClient
        .post().uri(uri)
        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
        .body(Mono.just(createCustomerDto), CreateCustomerDto.class)
        .exchange()
        .expectStatus().isBadRequest()
        .expectHeader().contentType(MediaType.APPLICATION_JSON);
  }
  
  /**
   * Signup with already registered email.
   */
  @Test
  @Order(3)
  void signupWithAlreadyRegisteredEmail() {
    String uri = "/auth/signup";
    CreateCustomerDto createCustomerDto = new CreateCustomerDto("Ajinkya",
        "ajinkyataranekar@gmail.com", "Abc@1234");
    
    webTestClient
        .post().uri(uri)
        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
        .body(Mono.just(createCustomerDto), CreateCustomerDto.class)
        .exchange()
        .expectStatus().isUnauthorized()
        .expectHeader().contentType(MediaType.APPLICATION_JSON);
  }
  
  /**
   * Signup with not verified email.
   */
  @Test
  @Order(4)
  void signupWithNotVerifiedEmail() {
    String uri = "/auth/signup";
    CreateCustomerDto createCustomerDto = new CreateCustomerDto("Ajinkya",
        "ajinkyataranekar12@gmail.com", "Abc@1234");
    
    webTestClient
        .post().uri(uri)
        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
        .body(Mono.just(createCustomerDto), CreateCustomerDto.class)
        .exchange()
        .expectStatus().isNotFound()
        .expectHeader().contentType(MediaType.APPLICATION_JSON);
  }
  
  /**
   * Verify email.
   */
  @Test
  @Order(5)
  public void verifyEmail() {
  
  }
  
  
  /**
   * Validate email by otp.
   */
  @Test
  @Order(6)
  public void validateEmailByOtp() {
  
  }
  
}
