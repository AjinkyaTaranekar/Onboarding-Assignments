package com.nuclei.assignment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.nuclei.assignment.constants.ExceptionsConstantsUtils;
import com.nuclei.assignment.constants.StringConstantsUtils;
import com.nuclei.assignment.constants.SuccessConstantsUtils;
import com.nuclei.assignment.entity.UserEntity;
import com.nuclei.assignment.enums.Courses;
import com.nuclei.assignment.enums.SortingOrder;
import com.nuclei.assignment.exception.CustomException;
import com.nuclei.assignment.service.useroperations.UserOperations;
import com.nuclei.assignment.service.useroperations.UserOperationsImpl;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 * Testing whole Application.
 */
public class ApplicationTest {
  private final InputStream systemIn = System.in;
  private final PrintStream systemOut = System.out;
  
  private ByteArrayInputStream testIn;
  private ByteArrayOutputStream testOut;
  
  /**
   * Sets up output.
   */
  @BeforeEach
  public void setUpOutput() {
    testOut = new ByteArrayOutputStream();
    System.setOut(new PrintStream(testOut));
  }
  
  private void provideInput(String data) {
    testIn = new ByteArrayInputStream(data.getBytes());
    System.setIn(testIn);
  }
  
  private String getOutput() {
    return testOut.toString();
  }
  
  /**
   * Restore system input output.
   */
  @AfterEach
  public void restoreSystemInputOutput() {
    System.setIn(systemIn);
    System.setOut(systemOut);
  }
  
  
  /**
   * Creating user gives success.
   */
  @Test
  public void creatingUserGivesSuccess() {
    final String[] test = {
      "1",
      "1",
      "Ajinkya",
      "21",
      "85, 114",
      "A,B,C,D"
    };
    final String testString = String.join("\n",test);
    provideInput(testString);
    
    Application.main(new String[0]);
    final String expectedMessage = SuccessConstantsUtils.CREATED_USER;
    assertTrue(getOutput().contains(expectedMessage));
  }
  
  
  /**
   * Adding two users with same roll number.
   */
  @Test
  public void addingTwoUsersWithSameRollNumber() {
    final String[] test = {
      "1",
      "2",
      "Ajinkya",
      "21",
      "85, 114",
      "A,B,C,D",
      "y",
      "1",
      "2",
      "Aditya",
      "21",
      "85, 114",
      "A,B,C,D"
    };
    final String testString = String.join("\n",test);
    provideInput(testString);
    
    Application.main(new String[0]);
    final String expectedMessage =
        String.format(ExceptionsConstantsUtils.ALREADY_PRESENT_ROLL_NUMBER, "2");
    assertTrue(getOutput().contains(expectedMessage));
  }
  
  /**
   * Adding two users with different name check if they are sorted.
   *
   * @throws CustomException the custom exception
   */
  @Test
  public void addingTwoUsersWithDifferentNameCheckIfTheyAreSorted() throws CustomException {
    UserOperations userOperations = new UserOperationsImpl();
    userOperations.addUser(
        new UserEntity("Ajinkya", 21, "85, Sch 114",
          1, new HashSet<>(Arrays.asList(Courses.A, Courses.B, Courses.C,
          Courses.D)))
    );
    userOperations.addUser(
        new UserEntity("Aditya", 15, "84, Sch 114",
          2, new HashSet<>(Arrays.asList(Courses.A, Courses.D, Courses.C,
          Courses.E)))
    );
    List<UserEntity> users = userOperations.getUsers();
    
    assertEquals("Ajinkya", users.get(1).getName());
  }
  
  /**
   * Adding two users with same name check if they are sorted.
   * If name are same then comparison is between roll numbers
   *
   * @throws CustomException the custom exception
   */
  @Test
  public void addingTwoUsersWithSameNameCheckIfTheyAreSorted() throws CustomException {
    UserOperations userOperations = new UserOperationsImpl();
    userOperations.addUser(
        new UserEntity("Ajinkya", 21, "85, Sch 114",
          1, new HashSet<>(Arrays.asList(Courses.A, Courses.B, Courses.C,
          Courses.D)))
    );
    userOperations.addUser(
        new UserEntity("Ajinkya", 15, "84, Sch 114",
          2, new HashSet<>(Arrays.asList(Courses.A, Courses.D, Courses.C,
          Courses.E)))
    );
    List<UserEntity> users = userOperations.getUsers();
    
    assertEquals(2, users.get(1).getRollNumber());
  }
  
  /**
   * Adding user with less course.
   */
  @Test
  public void addingUserWithLessCourse() {
    final String[] test = {
      "1",
      "3",
      "Ajinkya",
      "21",
      "85, 114",
      "A,B,C"
    };
    final String testString = String.join("\n",test);
    provideInput(testString);
    
    Application.main(new String[0]);
    final String expectedMessage =
        ExceptionsConstantsUtils.INVALID_COURSE_COUNT;
    assertTrue(getOutput().contains(expectedMessage));
  }
  
  /**
   * Adding user with empty name entry.
   */
  @Test
  public void addingUserWithEmptyNameEntry() {
    final String[] test = {
      "1",
      "5",
      "",
      "21",
      "85, 114",
      "A,B,C,D"
    };
    final String testString = String.join("\n",test);
    provideInput(testString);
  
    Application.main(new String[0]);
    final String expectedMessage =
        String.format(ExceptionsConstantsUtils.DATA_IS_NULL_OR_EMPTY,
          StringConstantsUtils.NAME);
    assertTrue(getOutput().contains(expectedMessage));
  }
  
  /**
   * Adding user with negative age.
   */
  @Test
  public void addingUserWithNegativeAge() {
    final String[] test = {
      "1",
      "6",
      "Ajinkya",
      "-21",
      "85, 114",
      "A,B,C,D"
    };
    final String testString = String.join("\n",test);
    provideInput(testString);
  
    Application.main(new String[0]);
    final String expectedMessage =
        String.format(ExceptionsConstantsUtils.NEGATIVE_PARAMETER,
          StringConstantsUtils.AGE, "-21");
    assertTrue(getOutput().contains(expectedMessage));
  }
  
  /**
   * Adding user with character age.
   */
  @Test
  public void addingUserWithCharacterAge() {
    final String[] test = {
      "1",
      "7",
      "Ajinkya",
      "abc",
      "85, 114",
      "A,B,C,D"
    };
    final String testString = String.join("\n",test);
    provideInput(testString);
  
    Application.main(new String[0]);
    final String expectedMessage =
        String.format(ExceptionsConstantsUtils.CHARACTER_PARAMETER,
          StringConstantsUtils.AGE, "abc");
    assertTrue(getOutput().contains(expectedMessage));
  }
  
  /**
   * Adding user with negative roll number.
   */
  @Test
  public void addingUserWithNegativeRollNumber() {
    final String[] test = {
      "1",
      "-1",
      "Ajinkya",
      "21",
      "85, 114",
      "A,B,C,D"
    };
    final String testString = String.join("\n",test);
    provideInput(testString);
  
    Application.main(new String[0]);
    final String expectedMessage =
        String.format(ExceptionsConstantsUtils.NEGATIVE_PARAMETER,
          StringConstantsUtils.ROLL_NUMBER, "-1");
    assertTrue(getOutput().contains(expectedMessage));
  }
  
  /**
   * Adding user with character roll number.
   */
  @Test
  public void addingUserWithCharacterRollNumber() {
    final String[] test = {
      "1",
      "abc",
      "Ajinkya",
      "21",
      "85, 114",
      "A,B,C,D"
    };
    final String testString = String.join("\n",test);
    provideInput(testString);
  
    Application.main(new String[0]);
    final String expectedMessage =
        String.format(ExceptionsConstantsUtils.CHARACTER_PARAMETER,
          StringConstantsUtils.ROLL_NUMBER, "abc");
    assertTrue(getOutput().contains(expectedMessage));
  }
  
  /**
   * Adding user with empty address.
   */
  @Test
  public void addingUserWithEmptyAddress() {
    final String[] test = {
      "1",
      "8",
      "Ajinkya",
      "21",
      "",
      "A,B,C,D"
    };
    final String testString = String.join("\n",test);
    provideInput(testString);
  
    Application.main(new String[0]);
    final String expectedMessage =
        String.format(ExceptionsConstantsUtils.DATA_IS_NULL_OR_EMPTY, StringConstantsUtils.ADDRESS);
    assertTrue(getOutput().contains(expectedMessage));
  }
  
  /**
   * Adding user with 3 courses.
   */
  @Test
  public void addingUserWith3Courses() {
    final String[] test = {
      "1",
      "8",
      "Ajinkya",
      "21",
      "85, Sch",
      "A,B,C"
    };
    final String testString = String.join("\n",test);
    provideInput(testString);
    
    Application.main(new String[0]);
    final String expectedMessage = ExceptionsConstantsUtils.INVALID_COURSE_COUNT;
    assertTrue(getOutput().contains(expectedMessage));
  }
  
  /**
   * Adding user with courses with same set.
   */
  @Test
  public void addingUserWithCoursesWithSameSet() {
    final String[] test = {
      "1",
      "8",
      "Ajinkya",
      "21",
      "85, Sch",
      "A,B,C,A"
    };
    final String testString = String.join("\n",test);
    provideInput(testString);
    
    Application.main(new String[0]);
    final String expectedMessage = ExceptionsConstantsUtils.INVALID_COURSE_COUNT;
    assertTrue(getOutput().contains(expectedMessage));
  }
  
  /**
   * Invalid menu options.
   */
  @Test
  public void invalidMenuOptions() {
    final String[] test = {
      "6"
    };
    final String testString = String.join("\n",test);
    provideInput(testString);
    
    Application.main(new String[0]);
    final String expectedMessage = ExceptionsConstantsUtils.INVALID_INPUT;
    assertTrue(getOutput().contains(expectedMessage));
  }
  
  /**
   * Displaying users based on column sorting order and columns.
   */
  @Test
  public void displayingUsersBasedOnColumnSortingOrderAndColumns() {
    final String[] test = {
      "1",
      "10",
      "Ajinkya",
      "21",
      "85, 114",
      "A,B,C,D",
      "y",
      "1",
      "11",
      "Aditya",
      "15",
      "84, 114",
      "A,E,C,F",
      "y",
      "2",
      "1",
      "ASC"
    };
    final String testString = String.join("\n",test);
    provideInput(testString);
  
    Application.main(new String[0]);
    final String expectedMessage = SuccessConstantsUtils.DISPLAY_USERS;
    assertTrue(getOutput().contains(expectedMessage));
  }
  
  /**
   * Displaying users but column is character.
   */
  @Test
  public void displayingUsersButColumnIsCharacter() {
    final String[] test = {
      "1",
      "10",
      "Ajinkya",
      "21",
      "85, 114",
      "A,B,C,D",
      "y",
      "1",
      "11",
      "Aditya",
      "15",
      "84, 114",
      "A,E,C,F",
      "y",
      "2",
      "1ab",
      "ASC"
    };
    final String testString = String.join("\n",test);
    provideInput(testString);
    
    Application.main(new String[0]);
    final String expectedMessage =
        String.format(ExceptionsConstantsUtils.CHARACTER_PARAMETER,
          StringConstantsUtils.COLUMN_NUMBER, "1ab");
    assertTrue(getOutput().contains(expectedMessage));
  }
  
  /**
   * Displaying users but column not found.
   */
  @Test
  public void displayingUsersButColumnNotFound() {
    final String[] test = {
      "1",
      "9",
      "Ajinkya",
      "21",
      "85, 114",
      "A,B,C,D",
      "y",
      "2",
      "-1"
    };
    final String testString = String.join("\n",test);
    provideInput(testString);
    
    Application.main(new String[0]);
    final String expectedMessage =
        String.format(ExceptionsConstantsUtils.NEGATIVE_PARAMETER,
          StringConstantsUtils.COLUMN_NUMBER, "-1");
    assertTrue(getOutput().contains(expectedMessage));
  }
  
  /**
   * Displaying users but sorting order not found.
   */
  @Test
  public void displayingUsersButSortingOrderNotFound() {
    final String[] test = {
      "1",
      "10",
      "Ajinkya",
      "21",
      "85, 114",
      "A,B,C,D",
      "y",
      "2",
      "1",
      "ascend"
    };
    final String testString = String.join("\n",test);
    provideInput(testString);
    
    Application.main(new String[0]);
    final String expectedMessage =
        String.format(ExceptionsConstantsUtils.INVALID_SORTING_ORDER, "ascend");
    assertTrue(getOutput().contains(expectedMessage));
  }
  
  /**
   * Displaying users sorting based on name in desc.
   *
   * @throws CustomException the custom exception
   */
  @Test
  public void displayingUsersSortingBasedOnNameInDesc() throws CustomException {
    UserOperations userOperations = new UserOperationsImpl();
    userOperations.addUser(
        new UserEntity("Ajinkya", 21, "85, Sch 114",
          1, new HashSet<>(Arrays.asList(Courses.A, Courses.B, Courses.C,
          Courses.D)))
    );
    userOperations.addUser(
        new UserEntity("Aditya", 15, "84, Sch 114",
          2, new HashSet<>(Arrays.asList(Courses.A, Courses.D, Courses.C,
          Courses.E)))
    );
    
    // columnNumber 1 is for Name
    int columnNumber = 1;
    SortingOrder sortingOrder = SortingOrder.DESC;
    List<UserEntity> users =
        userOperations.sortUsersBasedOnParameters(columnNumber, sortingOrder);
  
    assertEquals("Ajinkya", users.get(0).getName());
  }
  
  /**
   * Displaying users sorting based on name in asc.
   *
   * @throws CustomException the custom exception
   */
  @Test
  public void displayingUsersSortingBasedOnNameInAsc() throws CustomException {
    UserOperations userOperations = new UserOperationsImpl();
    userOperations.addUser(
        new UserEntity("Ajinkya", 21, "85, Sch 114",
          1, new HashSet<>(Arrays.asList(Courses.A, Courses.B, Courses.C,
          Courses.D)))
    );
    userOperations.addUser(
        new UserEntity("Aditya", 15, "84, Sch 114",
          2, new HashSet<>(Arrays.asList(Courses.A, Courses.D, Courses.C,
          Courses.E)))
    );
  
    // columnNumber 1 is for Name
    int columnNumber = 1;
    SortingOrder sortingOrder = SortingOrder.ASC;
    List<UserEntity> users =
        userOperations.sortUsersBasedOnParameters(columnNumber, sortingOrder);
  
    assertEquals("Ajinkya", users.get(1).getName());
  }
  
  /**
   * Displaying users sorting based on age.
   *
   * @throws CustomException the custom exception
   */
  @Test
  public void displayingUsersSortingBasedOnAge() throws CustomException {
    UserOperations userOperations = new UserOperationsImpl();
    userOperations.addUser(
        new UserEntity("Ajinkya", 21, "85, Sch 114",
          1, new HashSet<>(Arrays.asList(Courses.A, Courses.B, Courses.C,
          Courses.D)))
    );
    userOperations.addUser(
        new UserEntity("Aditya", 15, "84, Sch 114",
          2, new HashSet<>(Arrays.asList(Courses.A, Courses.D, Courses.C,
          Courses.E)))
    );
  
    // columnNumber 3 is for Age
    int columnNumber = 3;
    SortingOrder sortingOrder = SortingOrder.ASC;
    List<UserEntity> users =
        userOperations.sortUsersBasedOnParameters(columnNumber, sortingOrder);
  
    assertEquals("Ajinkya", users.get(1).getName());
  }
  
  /**
   * Displaying users sorting based on roll number.
   *
   * @throws CustomException the custom exception
   */
  @Test
  public void displayingUsersSortingBasedOnRollNumber() throws CustomException {
    UserOperations userOperations = new UserOperationsImpl();
    userOperations.addUser(
        new UserEntity("Ajinkya", 21, "85, Sch 114",
          1, new HashSet<>(Arrays.asList(Courses.A, Courses.B, Courses.C,
          Courses.D)))
    );
    userOperations.addUser(
        new UserEntity("Aditya", 15, "84, Sch 114",
          2, new HashSet<>(Arrays.asList(Courses.A, Courses.D, Courses.C,
          Courses.E)))
    );
    
    // columnNumber 2 is for RollNumber
    int columnNumber = 2;
    SortingOrder sortingOrder = SortingOrder.ASC;
    List<UserEntity> users =
        userOperations.sortUsersBasedOnParameters(columnNumber, sortingOrder);
    
    assertEquals("Ajinkya", users.get(0).getName());
  }
  
  /**
   * Displaying users sorting based on address.
   *
   * @throws CustomException the custom exception
   */
  @Test
  public void displayingUsersSortingBasedOnAddress() throws CustomException {
    UserOperations userOperations = new UserOperationsImpl();
    userOperations.addUser(
        new UserEntity("Ajinkya", 21, "85, Sch 114",
          1, new HashSet<>(Arrays.asList(Courses.A, Courses.B, Courses.C,
          Courses.D)))
    );
    userOperations.addUser(
        new UserEntity("Aditya", 15, "84, Sch 114",
          2, new HashSet<>(Arrays.asList(Courses.A, Courses.D, Courses.C,
          Courses.E)))
    );
    
    // columnNumber 4 is for Address
    int columnNumber = 4;
    SortingOrder sortingOrder = SortingOrder.ASC;
    List<UserEntity> users =
        userOperations.sortUsersBasedOnParameters(columnNumber, sortingOrder);
    
    assertEquals("Ajinkya", users.get(1).getName());
  }
  
  /**
   * Deleting user id present.
   */
  @Test
  public void deletingUserIdPresent() {
    final String[] test = {
      "1",
      "15",
      "Ajinkya",
      "21",
      "85, 114",
      "A,B,C,D",
      "y",
      "3",
      "15",
      "n"
    };
    final String testString = String.join("\n",test);
    provideInput(testString);
    
    Application.main(new String[0]);
    final String expectedMessage = SuccessConstantsUtils.DELETED_USER;
    assertTrue(getOutput().contains(expectedMessage));
  }
  
  /**
   * Deleting user id absent.
   */
  @Test
  public void deletingUserIdAbsent() {
    final String[] test = {
      "1",
      "18",
      "Ajinkya",
      "21",
      "85, 114",
      "A,B,C,D",
      "y",
      "3",
      "35",
      "n"
    };
    final String testString = String.join("\n",test);
    provideInput(testString);
    
    Application.main(new String[0]);
    final String expectedMessage =
        String.format(ExceptionsConstantsUtils.INVALID_ROLL_NUMBER, "35");
    assertTrue(getOutput().contains(expectedMessage));
  }
  
  /**
   * Saving user.
   */
  @Test
  public void savingUser() {
    final String[] test = {
      "1",
      "19",
      "Ajinkya",
      "21",
      "85, 114",
      "A,B,C,D",
      "y",
      "4",
      "n"
    };
    final String testString = String.join("\n",test);
    provideInput(testString);
    
    Application.main(new String[0]);
    final String expectedMessage = SuccessConstantsUtils.SAVE_USERS;
    assertTrue(getOutput().contains(expectedMessage));
  }
  
  /**
   * Saving user upon exit.
   */
  @Test
  public void savingUserUponExit() {
    final String[] test = {
      "1",
      "31",
      "Ajinkya",
      "21",
      "85, 114",
      "A,B,C,D",
      "y",
      "5",
      "y",
      "n"
    };
    final String testString = String.join("\n",test);
    provideInput(testString);
    
    Application.main(new String[0]);
    final String expectedMessage = SuccessConstantsUtils.SAVE_USERS;
    assertTrue(getOutput().contains(expectedMessage));
    
  }
  
}