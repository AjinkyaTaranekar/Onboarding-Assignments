package com.nuclei.assignment;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.nuclei.assignment.constants.ExceptionsConstantsUtils;
import com.nuclei.assignment.constants.StringConstantsUtils;
import com.nuclei.assignment.constants.SuccessConstantsUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ApplicationTest {
  private final InputStream systemIn = System.in;
  private final PrintStream systemOut = System.out;
  
  private ByteArrayInputStream testIn;
  private ByteArrayOutputStream testOut;
  
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
  
  @AfterEach
  public void restoreSystemInputOutput() {
    System.setIn(systemIn);
    System.setOut(systemOut);
  }
  
  
  @Test
  public void creatingUserGivesSuccess() {
    final String[] test = {
      "8",
      "1",
      "Ajinkya",
      "n"
    };
    final String testString = String.join("\n",test);
    provideInput(testString);
    
    Application.main(new String[0]);
    final String expectedMessage = SuccessConstantsUtils.CREATED_USER;
    assertTrue(getOutput().contains(expectedMessage));
  }
  
  
  @Test
  public void addingTwoUsersWithSameRollNumber() {
    final String[] test = {
      "8",
      "2",
      "Ajinkya",
      "n",
      "y",
      "8",
      "2",
      "Aditya",
      "n"
    };
    final String testString = String.join("\n",test);
    provideInput(testString);
    
    Application.main(new String[0]);
    final String expectedMessage =
        String.format(ExceptionsConstantsUtils.ID_ALREADY_EXISTS, "2");
    assertTrue(getOutput().contains(expectedMessage));
  }
  
  @Test
  public void addingUserWithEmptyNameEntry() {
    final String[] test = {
      "8",
      "5",
      "\n",
    };
    final String testString = String.join("\n",test);
    provideInput(testString);
    
    Application.main(new String[0]);
    final String expectedMessage = String.format(ExceptionsConstantsUtils.EMPTY_PARAMETER,
        StringConstantsUtils.NAME);
    assertTrue(getOutput().contains(expectedMessage));
  }
  
  @Test
  public void addingUserWithNegativeId() {
    final String[] test = {
      "8",
      "-1",
      "Ajinkya"
    };
    final String testString = String.join("\n",test);
    provideInput(testString);
    
    Application.main(new String[0]);
    final String expectedMessage =
        String.format(ExceptionsConstantsUtils.NEGATIVE_PARAMETER,
          StringConstantsUtils.ID, "-1");
    assertTrue(getOutput().contains(expectedMessage));
  }
  
  /**
   * Adding user with empty details.
   */
  @Test
  public void addingUserWithEmptyDetails() {
    final String[] test = {
      "8",
      "15",
      "Ajinkya",
      "y",
      "\n"
    };
    final String testString = String.join("\n",test);
    provideInput(testString);
    
    Application.main(new String[0]);
    final String expectedMessage =
        String.format(ExceptionsConstantsUtils.EMPTY_PARAMETER,
          StringConstantsUtils.USER_DETAILS);
    assertTrue(getOutput().contains(expectedMessage));
  }
  
  @Test
  public void addingUserWithInvalidDetails() {
    final String[] test = {
      "8",
      "15",
      "Ajinkya",
      "y",
      "phone"
    };
    final String testString = String.join("\n",test);
    provideInput(testString);
    
    Application.main(new String[0]);
    final String expectedMessage =
        String.format(ExceptionsConstantsUtils.DETAILS_NOT_FOUND, "[phone]");
    assertTrue(getOutput().contains(expectedMessage));
  }
  
  @Test
  public void addingUserWithDetails() {
    final String[] test = {
      "8",
      "15",
      "Ajinkya",
      "y",
      "phone, 8518076044",
      "n"
    };
    final String testString = String.join("\n",test);
    provideInput(testString);
    
    Application.main(new String[0]);
    final String expectedMessage = SuccessConstantsUtils.CREATED_USER;
    assertTrue(getOutput().contains(expectedMessage));
  }
  
  @Test
  public void addingUserWithCharacterId() {
    final String[] test = {
      "8",
      "abc",
      "Ajinkya"
    };
    final String testString = String.join("\n",test);
    provideInput(testString);
  
    Application.main(new String[0]);
    final String expectedMessage =
        String.format(ExceptionsConstantsUtils.CHARACTER_PARAMETER,
          StringConstantsUtils.ID, "abc");
    assertTrue(getOutput().contains(expectedMessage));
  }
  
  @Test
  public void invalidMenuInput() {
    final String[] test = {
      "9"
    };
    final String testString = String.join("\n",test);
    provideInput(testString);
    
    Application.main(new String[0]);
    final String expectedMessage =
        String.format(ExceptionsConstantsUtils.INVALID_INPUT, "9");
    assertTrue(getOutput().contains(expectedMessage));
  }
  
  
  @Test
  public void addingTwoUsersAndCreatingDependency() {
    final String[] test = {
      "8",
      "1",
      "Ajinkya",
      "n",
      "y",
      "8",
      "2",
      "Aditya",
      "n",
      "1",
      "2"
    };
    final String testString = String.join("\n",test);
    provideInput(testString);
  
    Application.main(new String[0]);
    final String expectedMessage = SuccessConstantsUtils.CREATED_DEPENDENCY;
    assertTrue(getOutput().contains(expectedMessage));
  }
  
  @Test
  public void addingTwoUsersAndCreatingDependencyButParentIdNotFound() {
    final String[] test = {
      "8",
      "1",
      "Ajinkya",
      "n",
      "y",
      "8",
      "2",
      "Aditya",
      "n",
      "3",
      "2"
    };
    final String testString = String.join("\n",test);
    provideInput(testString);
    
    Application.main(new String[0]);
    final String expectedMessage =
        String.format(ExceptionsConstantsUtils.INVALID_PARENT_ID, "3");
    assertTrue(getOutput().contains(expectedMessage));
  }
  
  @Test
  public void addingTwoUsersAndCreatingDependencyButChildIdNotFound() {
    final String[] test = {
      "8",
      "1",
      "Ajinkya",
      "n",
      "y",
      "8",
      "2",
      "Aditya",
      "n",
      "1",
      "3"
    };
    final String testString = String.join("\n",test);
    provideInput(testString);
    
    Application.main(new String[0]);
    final String expectedMessage =
        String.format(ExceptionsConstantsUtils.INVALID_CHILD_ID, "3");
    assertTrue(getOutput().contains(expectedMessage));
  }
  
  @Test
  public void addingTwoUsersAndCreatingDependencyButParentChildIdSame() {
    final String[] test = {
      "8",
      "1",
      "Ajinkya",
      "n",
      "y",
      "8",
      "2",
      "Aditya",
      "n",
      "1",
      "1"
    };
    final String testString = String.join("\n",test);
    provideInput(testString);
  
    Application.main(new String[0]);
    final String expectedMessage =
        String.format(ExceptionsConstantsUtils.PARENT_NOT_EQUALS_CHILD, "1", "1");
    assertTrue(getOutput().contains(expectedMessage));
  }
  
  @Test
  public void addingTwoUsersAndCreatingCyclicDependency() {
    final String[] test = {
      "8",
      "1",
      "Ajinkya",
      "n",
      "y",
      "8",
      "2",
      "Aditya",
      "n",
      "1",
      "2",
      "y",
      "7",
      "2",
      "1",
    };
    final String testString = String.join("\n",test);
    provideInput(testString);
    
    Application.main(new String[0]);
    final String expectedMessage =
        String.format(ExceptionsConstantsUtils.CYCLIC_DEPENDENCY, "2", "1");
    assertTrue(getOutput().contains(expectedMessage));
  }
  
  @Test
  public void addingFourUsersAndCreatingCyclicDependency() {
    final String[] test = {
      "8",
      "1",
      "Ajinkya1",
      "n",
      "y",
      "8",
      "2",
      "Ajinkya2",
      "n",
      "1",
      "2",
      "y",
      "8",
      "3",
      "Ajinkya3",
      "n",
      "2",
      "3",
      "y",
      "8",
      "4",
      "Ajinkya4",
      "n",
      "2",
      "4",
      "y",
      "7",
      "1",
      "4",
    };
    final String testString = String.join("\n",test);
    provideInput(testString);
    
    Application.main(new String[0]);
    final String expectedMessage =
        String.format(ExceptionsConstantsUtils.CYCLIC_DEPENDENCY, "1", "4");
    assertTrue(getOutput().contains(expectedMessage));
  }
  
  @Test
  public void addingTwoUsersAndCreatingDependencyCheckingParentOfUser() {
    final String[] test = {
      "8",
      "1",
      "Ajinkya",
      "n",
      "y",
      "8",
      "2",
      "Aditya",
      "n",
      "1",
      "2",
      "y",
      "1",
      "2"
    };
    final String testString = String.join("\n",test);
    provideInput(testString);
    
    Application.main(new String[0]);
    final String expectedMessage = "[1]";
    assertTrue(getOutput().contains(expectedMessage));
  }
  
  @Test
  public void addingTwoUsersAndCreatingDependencyCheckingChildrenOfUser() {
    final String[] test = {
      "8",
      "1",
      "Ajinkya",
      "n",
      "y",
      "8",
      "2",
      "Aditya",
      "n",
      "1",
      "2",
      "y",
      "2",
      "1"
    };
    final String testString = String.join("\n",test);
    provideInput(testString);
    
    Application.main(new String[0]);
    final String expectedMessage = "[2]";
    assertTrue(getOutput().contains(expectedMessage));
  }
  
  @Test
  public void addingTwoUsersAndCreatingDependencyCheckingAncestorOfUser() {
    final String[] test = {
      "8",
      "1",
      "Ajinkya",
      "n",
      "y",
      "8",
      "2",
      "Aditya",
      "n",
      "1",
      "2",
      "y",
      "3",
      "2"
    };
    final String testString = String.join("\n",test);
    provideInput(testString);
    
    Application.main(new String[0]);
    final String expectedMessage = "[1]";
    assertTrue(getOutput().contains(expectedMessage));
  }
  
  @Test
  public void addingTwoUsersAndCreatingDependencyCheckingDescendantsOfUser() {
    final String[] test = {
      "8",
      "1",
      "Ajinkya",
      "n",
      "y",
      "8",
      "2",
      "Aditya",
      "n",
      "1",
      "2",
      "y",
      "4",
      "1"
    };
    final String testString = String.join("\n",test);
    provideInput(testString);
    
    Application.main(new String[0]);
    final String expectedMessage = "[2]";
    assertTrue(getOutput().contains(expectedMessage));
  }
  
  @Test
  public void addingUserAndThenDeletingUser() {
    final String[] test = {
      "8",
      "1",
      "Ajinkya",
      "n",
      "y",
      "6",
      "1"
    };
    final String testString = String.join("\n",test);
    provideInput(testString);
    
    Application.main(new String[0]);
    final String expectedMessage = SuccessConstantsUtils.DELETED_USER;
    assertTrue(getOutput().contains(expectedMessage));
  }
  
  @Test
  public void addingUserAndThenDeletingUserButIdNotFound() {
    final String[] test = {
      "8",
      "1",
      "Ajinkya",
      "n",
      "y",
      "6",
      "2"
    };
    final String testString = String.join("\n",test);
    provideInput(testString);
    
    Application.main(new String[0]);
    final String expectedMessage =
        String.format(ExceptionsConstantsUtils.INVALID_ID, "2");
    assertTrue(getOutput().contains(expectedMessage));
  }
  
  @Test
  public void addingTwoUsersAndCreatingDependencyThenDeletingDependency() {
    final String[] test = {
      "8",
      "1",
      "Ajinkya",
      "n",
      "y",
      "8",
      "2",
      "Aditya",
      "n",
      "1",
      "2",
      "y",
      "6",
      "1",
      "2"
    };
    final String testString = String.join("\n",test);
    provideInput(testString);
    
    Application.main(new String[0]);
    final String expectedMessage = SuccessConstantsUtils.DELETED_DEPENDENCY;
    assertTrue(getOutput().contains(expectedMessage));
  }
  
  @Test
  public void addingTwoUsersAndCreatingDependencyThenDeletingDependencyButParentIdNotFound() {
    final String[] test = {
      "8",
      "1",
      "Ajinkya",
      "n",
      "y",
      "8",
      "2",
      "Aditya",
      "n",
      "1",
      "2",
      "y",
      "5",
      "3",
      "2"
    };
    final String testString = String.join("\n",test);
    provideInput(testString);
  
    Application.main(new String[0]);
    final String expectedMessage =
        String.format(ExceptionsConstantsUtils.INVALID_PARENT_ID, "3");
    assertTrue(getOutput().contains(expectedMessage));
  }
  
  @Test
  public void addingTwoUsersThenDeletingDependencyButNoDependency() {
    final String[] test = {
      "8",
      "1",
      "Ajinkya",
      "n",
      "y",
      "8",
      "2",
      "Aditya",
      "n",
      "1",
      "3",
      "y",
      "5",
      "1",
      "2"
    };
    final String testString = String.join("\n",test);
    provideInput(testString);
    
    Application.main(new String[0]);
    final String expectedMessage =
        String.format(ExceptionsConstantsUtils.NO_DEPENDENCY, "1", "2");
    assertTrue(getOutput().contains(expectedMessage));
  }
  
  @Test
  public void addingTwoUsersAndCreatingDependencyThenDeletingDependencyButChildIdNotFound() {
    final String[] test = {
      "8",
      "1",
      "Ajinkya",
      "n",
      "y",
      "8",
      "2",
      "Aditya",
      "n",
      "1",
      "2",
      "y",
      "5",
      "1",
      "3"
    };
    final String testString = String.join("\n",test);
    provideInput(testString);
    
    Application.main(new String[0]);
    final String expectedMessage =
        String.format(ExceptionsConstantsUtils.INVALID_CHILD_ID, "3");
    assertTrue(getOutput().contains(expectedMessage));
  }
  
}