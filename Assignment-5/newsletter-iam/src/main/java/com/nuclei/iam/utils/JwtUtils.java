package com.nuclei.iam.utils;

import com.nuclei.iam.constants.ResponseConstantsUtils;
import com.nuclei.iam.constants.SecurityConstantsUtils;
import com.nuclei.iam.entity.CustomerEntity;
import com.nuclei.iam.exceptions.AuthorizationException;
import com.nuclei.iam.exceptions.ValidationException;
import com.nuclei.iam.validation.Validation;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.Date;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

/**
 * The type Jwt utils.
 */
@Component
@AllArgsConstructor
public class JwtUtils implements Serializable {
  
  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = -2550185165626007488L;
  /**
   * The Validation.
   */
  private final Validation validation;
  
  /**
   * Extract token string.
   *
   * @param request the request
   *
   * @return the string
   *
   * @throws AuthorizationException the authorization exception
   * @throws ValidationException    the validation exception
   */
  public String extractToken(final HttpServletRequest request) throws AuthorizationException,
      ValidationException {
    final String authHeader = request.getHeader(SecurityConstantsUtils.HEADER_STRING);
    if (Objects.isNull(authHeader)) {
      throw new AuthorizationException(ResponseConstantsUtils.UNAUTHORIZED_MESSAGE,
          HttpStatus.UNAUTHORIZED);
    }
    final String[] authHeaderList = authHeader.split(" ");
    if (authHeaderList.length != SecurityConstantsUtils.MINIMUM_LENGTH_IN_HEADERS) {
      throw new AuthorizationException(ResponseConstantsUtils.UNAUTHORIZED_MESSAGE,
          HttpStatus.UNAUTHORIZED);
    }
    if (authHeaderList[0].equals(SecurityConstantsUtils.TOKEN_PREFIX)) {
      throw new AuthorizationException(ResponseConstantsUtils.UNAUTHORIZED_MESSAGE,
          HttpStatus.UNAUTHORIZED);
    }
    validation.validateJwt(authHeaderList[1]);
    return authHeaderList[1];
  }
  
  /**
   * Gets id from token.
   *
   * @param token the token
   *
   * @return the id from token
   */
  public Integer getIdFromToken(String token) {
    return Integer.parseInt(getClaimFromToken(token, Claims::getIssuer));
  }
  
  /**
   * Gets email id from token.
   *
   * @param token the token
   *
   * @return the email id from token
   */
  public String getEmailIdFromToken(String token) {
    return getClaimFromToken(token, Claims::getSubject);
  }
  
  /**
   * Gets expiration date from token.
   *
   * @param token the token
   *
   * @return the expiration date from token
   */
  public Date getExpirationDateFromToken(String token) {
    return getClaimFromToken(token, Claims::getExpiration);
  }
  
  /**
   * Gets claim from token.
   *
   * @param <T>            the type parameter
   * @param token          the token
   * @param claimsResolver the claims resolver
   *
   * @return the claim from token
   */
  public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
    final Claims claims = getAllClaimsFromToken(token);
    return claimsResolver.apply(claims);
  }
  
  /**
   * Gets all claims from token.
   * for retrieving any information from token we will need the secret key
   *
   * @param token the token
   *
   * @return the all claims from token
   */
  private Claims getAllClaimsFromToken(String token) {
    return Jwts.parser()
        .setSigningKey(SecurityConstantsUtils.SECRET)
        .parseClaimsJws(token)
        .getBody();
  }
  
  /**
   * Is token expired boolean.
   * This checks if the token has expired
   *
   * @param token the token
   *
   * @return the boolean
   */
  private Boolean isTokenExpired(String token) {
    final Date expiration = getExpirationDateFromToken(token);
    return expiration.before(new Date());
  }
  
  /**
   * Generate token string.
   *
   * @param customerEntity the customer entity
   *
   * @return the string
   */
  public String generateToken(CustomerEntity customerEntity) {
    final Map<String, Object> claims = new ConcurrentHashMap<>();
    return doGenerateToken(claims, customerEntity.getEmailId(),
        customerEntity.getId().toString());
  }
  
  /**
   * Upon creating the token -
   * 1. Define claims of the token, like Issuer, Expiration, Subject, and the ID
   * 2. Sign the JWT using the HS512 algorithm and secret key.
   * 3. According to JWS Compact Serialization(https://tools.ietf.org/html/draft-ietf-jose-json-web-signature-41#section-3.1)
   * compaction of the JWT to a URL-safe string
   *
   * @param claims  the claims
   * @param subject the subject
   * @param id      the id
   *
   * @return the string
   */
  private String doGenerateToken(Map<String, Object> claims, String subject,
                                 String id) {
    return Jwts.builder()
        .setClaims(claims)
        .setSubject(subject)
        .setIssuer(id)
        .setIssuedAt(new Date(System.currentTimeMillis()))
        .setExpiration(new Date(System.currentTimeMillis()
            + SecurityConstantsUtils.JWT_EXPIRATION_TIME * 1000))
        .signWith(SignatureAlgorithm.HS512, SecurityConstantsUtils.SECRET)
        .compact();
  }
  
  /**
   * Validate token boolean.
   *
   * @param token   the token
   * @param emailId the email id
   *
   * @return the boolean
   */
  public Boolean validateToken(String token, String emailId) {
    try {
      validation.validateJwt(token);
    } catch (ValidationException exception) {
      return false;
    }
    final String emailIdFromToken = getEmailIdFromToken(token);
    return emailIdFromToken.equals(emailId) && !isTokenExpired(token);
  }
}

