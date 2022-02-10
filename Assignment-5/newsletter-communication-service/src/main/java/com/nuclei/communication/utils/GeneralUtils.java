package com.nuclei.communication.utils;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.nuclei.communication.dto.response.ApiResponseDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

/**
 * The type General utils.
 */
@Component
public class GeneralUtils {
  
  public ResponseEntity<ApiResponseDto> apiResponseEntityBuilder(
      HttpStatus httpStatus, String message, Object body) {
    return ResponseEntity.status(httpStatus)
        .body(new ApiResponseDto(message, body));
  }
}
