package com.nuclei.iam.utils;

import com.nuclei.iam.constants.SecurityConstantsUtils;
import com.nuclei.iam.dto.response.ApiResponseDto;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Objects;
import java.util.Random;
import java.util.Set;

/**
 * The type General utils.
 */
@Component
public class GeneralUtils {
  
  /**
   * Get null property names string [ ].
   *
   * @param source the source
   *
   * @return the string [ ]
   */
  private static String[] getNullPropertyNames(Object source) {
    final BeanWrapper src = new BeanWrapperImpl(source);
    final java.beans.PropertyDescriptor[] propertyDescriptors =
        src.getPropertyDescriptors();
    final Set<String> emptyNames = new HashSet<>();
    for (final java.beans.PropertyDescriptor propertyDescriptor :
        propertyDescriptors) {
      final Object srcValue =
          src.getPropertyValue(propertyDescriptor.getName());
      if (Objects.isNull(srcValue)) {
        emptyNames.add(propertyDescriptor.getName());
      }
    }
    final String[] result = new String[emptyNames.size()];
    return emptyNames.toArray(result);
  }
  
  /**
   * Copy not null properties.
   *
   * @param source      the source
   * @param destination the destination
   */
  public void copyNotNullProperties(Object source, Object destination) {
    BeanUtils.copyProperties(source, destination,
        getNullPropertyNames(source));
  }
  
  /**
   * Api response entity builder response entity.
   *
   * @param httpStatus the http status
   * @param message    the message
   * @param body       the body
   *
   * @return the response entity
   */
  public ResponseEntity<ApiResponseDto> apiResponseEntityBuilder(
      HttpStatus httpStatus, String message, Object body) {
    return ResponseEntity.status(httpStatus)
        .body(new ApiResponseDto(message, body));
  }
  
  /**
   * Generate otp integer.
   *
   * @return the integer
   */
  public Integer generateOtp() {
    final Random random = new Random();
    final StringBuilder otp =
        new StringBuilder(SecurityConstantsUtils.OTP_LENGTH);
    for (int index = 0; index < SecurityConstantsUtils.OTP_LENGTH; index++) {
      Character randomDigit = SecurityConstantsUtils.OTP_NUMBERS_ALLOWED.charAt(
          random.nextInt(SecurityConstantsUtils.OTP_NUMBERS_ALLOWED.length()));
      if (index == 0 && randomDigit == '0') {
        randomDigit = '1';
      }
      otp.append(randomDigit);
    }
    return Integer.parseInt(otp.toString());
  }
  
  /**
   * Generate raw password string.
   *
   * @return the string
   */
  public String generateRawPassword() {
    final int length = 8;
    final boolean useLetters = true;
    final boolean useNumbers = true;
    return RandomStringUtils.random(length, useLetters, useNumbers);
  }
}
