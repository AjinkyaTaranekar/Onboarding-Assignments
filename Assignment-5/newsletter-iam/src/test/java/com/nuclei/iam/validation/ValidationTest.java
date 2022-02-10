package com.nuclei.iam.validation;

import com.nuclei.iam.constants.ResponseConstantsUtils;
import com.nuclei.iam.constants.StringConstantsUtils;
import com.nuclei.iam.exceptions.ValidationException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The type Validation test.
 */
public class ValidationTest {
  
  /**
   * The constant validation.
   */
  private static final Validation validation;
  
  static {
    validation = new ValidationImpl();
  }
  
  /**
   * Validate id when it is null.
   */
  @Test
  public void validateIdWhenItIsNull() {
    final Integer input = null;
    final Exception exception = assertThrows(ValidationException.class,
        () -> validation.validateId(input));
    
    final String expectedMessage =
        String.format(ResponseConstantsUtils.EMPTY_OR_NULL_PARAMETER,
            StringConstantsUtils.ID);
    final String actualMessage = exception.getMessage();
    
    assertEquals(expectedMessage, actualMessage);
  }
  
  /**
   * Validate name when it is null.
   */
  @Test
  public void validateNameWhenItIsNull() {
    final String input = null;
    final Exception exception = assertThrows(ValidationException.class,
        () -> validation.validateName(input));
    
    final String expectedMessage =
        String.format(ResponseConstantsUtils.EMPTY_OR_NULL_PARAMETER,
            StringConstantsUtils.NAME);
    final String actualMessage = exception.getMessage();
    
    assertEquals(expectedMessage, actualMessage);
  }
  
  /**
   * Validate email id when it is null.
   */
  @Test
  public void validateEmailIdWhenItIsNull() {
    final String input = null;
    final Exception exception = assertThrows(ValidationException.class,
        () -> validation.validateEmailId(input));
    
    final String expectedMessage =
        String.format(ResponseConstantsUtils.EMPTY_OR_NULL_PARAMETER,
            StringConstantsUtils.EMAIL);
    final String actualMessage = exception.getMessage();
    
    assertEquals(expectedMessage, actualMessage);
  }
  
  /**
   * Validate password when it is null.
   */
  @Test
  public void validatePasswordWhenItIsNull() {
    final String input = null;
    final Exception exception = assertThrows(ValidationException.class,
        () -> validation.validatePassword(input));
    
    final String expectedMessage =
        String.format(ResponseConstantsUtils.EMPTY_OR_NULL_PARAMETER,
            StringConstantsUtils.PASSWORD);
    final String actualMessage = exception.getMessage();
    
    assertEquals(expectedMessage, actualMessage);
  }
  
  /**
   * Validate jwt when it is null.
   */
  @Test
  public void validateJwtWhenItIsNull() {
    final String input = null;
    final Exception exception = assertThrows(ValidationException.class,
        () -> validation.validateJwt(input));
    
    final String expectedMessage =
        String.format(ResponseConstantsUtils.EMPTY_OR_NULL_PARAMETER,
            StringConstantsUtils.JWT);
    final String actualMessage = exception.getMessage();
    
    assertEquals(expectedMessage, actualMessage);
  }
  
  /**
   * Validate id when it is zero.
   */
  @Test
  public void validateIdWhenItIsZero() {
    final Integer input = 0;
    final Exception exception = assertThrows(ValidationException.class,
        () -> validation.validateId(input));
    
    final String expectedMessage =
        String.format(ResponseConstantsUtils.NEGATIVE_OR_ZERO_PARAMETER,
            StringConstantsUtils.ID, input);
    final String actualMessage = exception.getMessage();
    
    assertEquals(expectedMessage, actualMessage);
  }
  
  /**
   * Validate id when it is negative.
   */
  @Test
  public void validateIdWhenItIsNegative() {
    final Integer input = -5;
    final Exception exception = assertThrows(ValidationException.class,
        () -> validation.validateId(input));
    
    final String expectedMessage =
        String.format(ResponseConstantsUtils.NEGATIVE_OR_ZERO_PARAMETER,
            StringConstantsUtils.ID, input);
    final String actualMessage = exception.getMessage();
    
    assertEquals(expectedMessage, actualMessage);
  }
  
  /**
   * Validate id when it is positive.
   */
  @Test
  public void validateIdWhenItIsPositive() {
    final Integer input = 5;
    assertDoesNotThrow(() -> validation.validateId(input));
  }
  
  /**
   * Validate name when it is blank.
   */
  @Test
  public void validateNameWhenItIsBlank() {
    final String input = "";
    final Exception exception = assertThrows(ValidationException.class,
        () -> validation.validateName(input));
    
    final String expectedMessage =
        String.format(ResponseConstantsUtils.EMPTY_OR_NULL_PARAMETER,
            StringConstantsUtils.NAME);
    final String actualMessage = exception.getMessage();
    
    assertEquals(expectedMessage, actualMessage);
  }
  
  /**
   * Validate name when it contains white spaces.
   */
  @Test
  public void validateNameWhenItContainsWhiteSpaces() {
    final String input = " ";
    final Exception exception = assertThrows(ValidationException.class,
        () -> validation.validateName(input));
    
    final String expectedMessage =
        String.format(ResponseConstantsUtils.EMPTY_OR_NULL_PARAMETER,
            StringConstantsUtils.NAME);
    final String actualMessage = exception.getMessage();
    
    assertEquals(expectedMessage, actualMessage);
  }
  
  /**
   * Validate name when it is correct.
   */
  @Test
  public void validateNameWhenItIsCorrect() {
    final String input = "abc";
    assertDoesNotThrow(() -> validation.validateName(input));
  }
  
  /**
   * Validate email when it is blank.
   */
  @Test
  public void validateEmailWhenItIsBlank() {
    final String input = "";
    final Exception exception = assertThrows(ValidationException.class,
        () -> validation.validateEmailId(input));
    
    final String expectedMessage =
        String.format(ResponseConstantsUtils.EMPTY_OR_NULL_PARAMETER,
            StringConstantsUtils.EMAIL);
    final String actualMessage = exception.getMessage();
    
    assertEquals(expectedMessage, actualMessage);
  }
  
  /**
   * Validate email when it contains white spaces.
   */
  @Test
  public void validateEmailWhenItContainsWhiteSpaces() {
    final String input = " ";
    final Exception exception = assertThrows(ValidationException.class,
        () -> validation.validateEmailId(input));
    
    final String expectedMessage =
        String.format(ResponseConstantsUtils.EMPTY_OR_NULL_PARAMETER,
            StringConstantsUtils.EMAIL);
    final String actualMessage = exception.getMessage();
    
    assertEquals(expectedMessage, actualMessage);
  }
  
  /**
   * Validate email when it does not have domain.
   */
  @Test
  public void validateEmailWhenItDoesNotHaveDomain() {
    final String input = "abc@gmail";
    final Exception exception = assertThrows(ValidationException.class,
        () -> validation.validateEmailId(input));
    
    final String expectedMessage =
        String.format(ResponseConstantsUtils.INVALID_PARAMETER,
            StringConstantsUtils.EMAIL, input);
    final String actualMessage = exception.getMessage();
    
    assertEquals(expectedMessage, actualMessage);
  }
  
  /**
   * Validate email when it does not have name.
   */
  @Test
  public void validateEmailWhenItDoesNotHaveName() {
    final String input = "@gmail.com";
    final Exception exception = assertThrows(ValidationException.class,
        () -> validation.validateEmailId(input));
    
    final String expectedMessage =
        String.format(ResponseConstantsUtils.INVALID_PARAMETER,
            StringConstantsUtils.EMAIL, input);
    final String actualMessage = exception.getMessage();
    
    assertEquals(expectedMessage, actualMessage);
  }
  
  /**
   * Validate email when it does not have at the rate sign.
   */
  @Test
  public void validateEmailWhenItDoesNotHaveAtTheRateSign() {
    final String input = "abcgmail.com";
    final Exception exception = assertThrows(ValidationException.class,
        () -> validation.validateEmailId(input));
    
    final String expectedMessage =
        String.format(ResponseConstantsUtils.INVALID_PARAMETER,
            StringConstantsUtils.EMAIL, input);
    final String actualMessage = exception.getMessage();
    
    assertEquals(expectedMessage, actualMessage);
  }
  
  /**
   * Validate email when it does not have host.
   */
  @Test
  public void validateEmailWhenItDoesNotHaveHost() {
    final String input = "abc@.com";
    final Exception exception = assertThrows(ValidationException.class,
        () -> validation.validateEmailId(input));
    
    final String expectedMessage =
        String.format(ResponseConstantsUtils.INVALID_PARAMETER,
            StringConstantsUtils.EMAIL, input);
    final String actualMessage = exception.getMessage();
    
    assertEquals(expectedMessage, actualMessage);
  }
  
  /**
   * Validate email when it is correct.
   */
  @Test
  public void validateEmailWhenItIsCorrect() {
    final String input = "abc@gmail.com";
    assertDoesNotThrow(() -> validation.validateEmailId(input));
  }
  
  /**
   * Validate email when it has new gmail plus sign.
   */
  @Test
  public void validateEmailWhenItHasNewGmailPlusSign() {
    final String input = "abc+jenkins@gmail.com";
    assertDoesNotThrow(() -> validation.validateEmailId(input));
  }
  
  /**
   * Validate email when it has dot in name.
   */
  @Test
  public void validateEmailWhenItHasDotInName() {
    final String input = "abc.xyz@gmail.com";
    assertDoesNotThrow(() -> validation.validateEmailId(input));
  }
  
  /**
   * Validate email when it has dot in domain.
   */
  @Test
  public void validateEmailWhenItHasDotInDomain() {
    final String input = "abc@a.b.c.com";
    assertDoesNotThrow(() -> validation.validateEmailId(input));
  }
  
  /**
   * Validate email when it is integer.
   */
  @Test
  public void validateEmailWhenItIsInteger() {
    final String input = "abc123@gmail.com";
    assertDoesNotThrow(() -> validation.validateEmailId(input));
  }
  
  /**
   * Validate email when it has dot in com.
   */
  @Test
  public void validateEmailWhenItHasDotInCom() {
    final String input = "abc@gmail.co.uk";
    assertDoesNotThrow(() -> validation.validateEmailId(input));
  }
  
  /**
   * Validate password when it is blank.
   */
  @Test
  public void validatePasswordWhenItIsBlank() {
    final String input = "";
    final Exception exception = assertThrows(ValidationException.class,
        () -> validation.validatePassword(input));
    
    final String expectedMessage =
        String.format(ResponseConstantsUtils.EMPTY_OR_NULL_PARAMETER,
            StringConstantsUtils.PASSWORD);
    final String actualMessage = exception.getMessage();
    
    assertEquals(expectedMessage, actualMessage);
  }
  
  /**
   * Validate password when it contains white spaces.
   */
  @Test
  public void validatePasswordWhenItContainsWhiteSpaces() {
    final String input = " ";
    final Exception exception = assertThrows(ValidationException.class,
        () -> validation.validatePassword(input));
    
    final String expectedMessage =
        String.format(ResponseConstantsUtils.EMPTY_OR_NULL_PARAMETER,
            StringConstantsUtils.PASSWORD);
    final String actualMessage = exception.getMessage();
    
    assertEquals(expectedMessage, actualMessage);
  }
  
  /**
   * Validate password when it does not have capital letter.
   */
  @Test
  public void validatePasswordWhenItDoesNotHaveCapitalLetter() {
    final String input = "abc12345";
    final Exception exception = assertThrows(ValidationException.class,
        () -> validation.validatePassword(input));
    
    final String expectedMessage =
        String.format(ResponseConstantsUtils.INVALID_PARAMETER,
            StringConstantsUtils.PASSWORD, input) + StringConstantsUtils.PASSWORD_POLICY;
    final String actualMessage = exception.getMessage();
    
    assertEquals(expectedMessage, actualMessage);
  }
  
  /**
   * Validate password when it does not have small letter.
   */
  @Test
  public void validatePasswordWhenItDoesNotHaveSmallLetter() {
    final String input = "ABC12345";
    final Exception exception = assertThrows(ValidationException.class,
        () -> validation.validatePassword(input));
    
    final String expectedMessage =
        String.format(ResponseConstantsUtils.INVALID_PARAMETER,
            StringConstantsUtils.PASSWORD, input) + StringConstantsUtils.PASSWORD_POLICY;
    final String actualMessage = exception.getMessage();
    
    assertEquals(expectedMessage, actualMessage);
  }
  
  /**
   * Validate password when it does not have number.
   */
  @Test
  public void validatePasswordWhenItDoesNotHaveNumber() {
    final String input = "Abcdefgh";
    final Exception exception = assertThrows(ValidationException.class,
        () -> validation.validatePassword(input));
    
    final String expectedMessage =
        String.format(ResponseConstantsUtils.INVALID_PARAMETER,
            StringConstantsUtils.PASSWORD, input) + StringConstantsUtils.PASSWORD_POLICY;
    final String actualMessage = exception.getMessage();
    
    assertEquals(expectedMessage, actualMessage);
  }
  
  /**
   * Validate password when it is not 8 character long.
   */
  @Test
  public void validatePasswordWhenItIsNot8CharacterLong() {
    final String input = "Abc@12";
    final Exception exception = assertThrows(ValidationException.class,
        () -> validation.validatePassword(input));
    
    final String expectedMessage =
        String.format(ResponseConstantsUtils.INVALID_PARAMETER,
            StringConstantsUtils.PASSWORD, input) + StringConstantsUtils.PASSWORD_POLICY;
    final String actualMessage = exception.getMessage();
    
    assertEquals(expectedMessage, actualMessage);
  }
  
  /**
   * Validate password when it does not have special character.
   */
  @Test
  public void validatePasswordWhenItDoesNotHaveSpecialCharacter() {
    final String input = "Abc12345";
    final Exception exception = assertThrows(ValidationException.class,
        () -> validation.validatePassword(input));
    
    final String expectedMessage =
        String.format(ResponseConstantsUtils.INVALID_PARAMETER,
            StringConstantsUtils.PASSWORD, input) + StringConstantsUtils.PASSWORD_POLICY;
    final String actualMessage = exception.getMessage();
    
    assertEquals(expectedMessage, actualMessage);
  }
  
  /**
   * Validate email when it is as per password policy.
   */
  @Test
  public void validateEmailWhenItIsAsPerPasswordPolicy() {
    final String input = "Abc@1234";
    assertDoesNotThrow(() -> validation.validatePassword(input));
  }
  
  /**
   * Validate jwt when it is blank.
   */
  @Test
  public void validateJwtWhenItIsBlank() {
    final String input = "";
    final Exception exception = assertThrows(ValidationException.class,
        () -> validation.validateJwt(input));
    
    final String expectedMessage =
        String.format(ResponseConstantsUtils.EMPTY_OR_NULL_PARAMETER,
            StringConstantsUtils.JWT);
    final String actualMessage = exception.getMessage();
    
    assertEquals(expectedMessage, actualMessage);
  }
  
  /**
   * Validate jwt when it contains white spaces.
   */
  @Test
  public void validateJwtWhenItContainsWhiteSpaces() {
    final String input = " ";
    final Exception exception = assertThrows(ValidationException.class,
        () -> validation.validateJwt(input));
    
    final String expectedMessage =
        String.format(ResponseConstantsUtils.EMPTY_OR_NULL_PARAMETER,
            StringConstantsUtils.JWT);
    final String actualMessage = exception.getMessage();
    
    assertEquals(expectedMessage, actualMessage);
  }
  
  /**
   * Validate jwt when it contains special character.
   */
  @Test
  public void validateJwtWhenItContainsSpecialCharacter() {
    final String input = "dfghjk@.hgjh@.hgfd65g";
    final Exception exception = assertThrows(ValidationException.class,
        () -> validation.validateJwt(input));
    
    final String expectedMessage =
        String.format(ResponseConstantsUtils.INVALID_PARAMETER,
            StringConstantsUtils.JWT, input);
    final String actualMessage = exception.getMessage();
    
    assertEquals(expectedMessage, actualMessage);
  }
  
  /**
   * Validate jwt when it does not have 2 dots.
   */
  @Test
  public void validateJwtWhenItDoesNotHave2Dots() {
    final String input = "dfghjk.hgjhhgfd65g";
    final Exception exception = assertThrows(ValidationException.class,
        () -> validation.validateJwt(input));
    
    final String expectedMessage =
        String.format(ResponseConstantsUtils.INVALID_PARAMETER,
            StringConstantsUtils.JWT, input);
    final String actualMessage = exception.getMessage();
    
    assertEquals(expectedMessage, actualMessage);
  }
  
  /**
   * Validate jwt when it does have more than 3 dots.
   */
  @Test
  public void validateJwtWhenItDoesHaveMoreThan3Dots() {
    final String input = "dfghjk.hgjhhg..fd65g";
    final Exception exception = assertThrows(ValidationException.class,
        () -> validation.validateJwt(input));
    
    final String expectedMessage =
        String.format(ResponseConstantsUtils.INVALID_PARAMETER,
            StringConstantsUtils.JWT, input);
    final String actualMessage = exception.getMessage();
    
    assertEquals(expectedMessage, actualMessage);
  }
  
  /**
   * Validate jwt when it is correct.
   */
  @Test
  public void validateJwtWhenItIsCorrect() {
    final String input = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMjM0NT"
        + "Y3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiYWRtaW4iOnRydWUsImlhdCI6MTY0NDA4MTI2MS"
        + "wiZXhwIjoxNjQ0MDg0ODYxfQ.Xn91xfMLvrDueTAhj24f04zucrGkZijPHdYvZFiFlG4";
    assertDoesNotThrow(() -> validation.validateJwt(input));
  }
  
  /**
   * Validate jwt when it has underscores.
   */
  @Test
  public void validateJwtWhenItHasUnderscores() {
    final String input = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMjM0NT"
        + "Y3ODkwIiwibmFtZSI6IkpvaG4gR_G9lIiwiYWRta4iOn_RydWUsImlhdCI6MTY0NDA4MTI2MS"
        + "wiZXhwIjoxNjQ0M_g0ODYxfQ.Xn91xfMLvrDueTAhj24f04zucrGkZijPHdYvZFiFlG4";
    assertDoesNotThrow(() -> validation.validateJwt(input));
  }
  
  /**
   * Validate jwt when it has dash.
   */
  @Test
  public void validateJwtWhenItHasDash() {
    final String input = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMjM0NT"
        + "Y3ODkwIiw-bmFtZSI6IkpvaG4gRG9lIiwiY-RtaW4iOnRydWUsImlhdCI6MTY0NDA4MTI2MS"
        + "wiZXhwIjo-NjQ0MDg0ODYxfQ.Xn91xfMLvr-ueTAhj24f04zucrGkZijPHdYvZFiFlG4";
    assertDoesNotThrow(() -> validation.validateJwt(input));
  }
}
