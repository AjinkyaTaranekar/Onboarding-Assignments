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

/**
 * The type Application test.
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
   * Creating node gives success.
   */
  @Test
  public void creatingNodeGivesSuccess() {
    final String[] test = {
      "8",
      "1",
      "Ajinkya",
      "n"
    };
    final String testString = String.join("\n",test);
    provideInput(testString);
    
    Application.main(new String[0]);
    final String expectedMessage = SuccessConstantsUtils.CREATED_NODE;
    assertTrue(getOutput().contains(expectedMessage));
  }
  
  
  /**
   * Adding two nodes with same roll number.
   */
  @Test
  public void addingTwoNodesWithSameRollNumber() {
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
  
  /**
   * Adding node with empty name entry.
   */
  @Test
  public void addingNodeWithEmptyNameEntry() {
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
  
  /**
   * Adding node with negative id.
   */
  @Test
  public void addingNodeWithNegativeId() {
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
   * Adding node with decimal id.
   */
  @Test
  public void addingNodeWithDecimalId() {
    final String[] test = {
      "8",
      "1.5",
      "Ajinkya"
    };
    final String testString = String.join("\n",test);
    provideInput(testString);
    
    Application.main(new String[0]);
    final String expectedMessage =
        String.format(ExceptionsConstantsUtils.DECIMAL_PARAMETER,
          StringConstantsUtils.ID, "1.5");
    assertTrue(getOutput().contains(expectedMessage));
  }
  
  /**
   * Adding node with empty details.
   */
  @Test
  public void addingNodeWithEmptyDetails() {
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
          StringConstantsUtils.KEY);
    assertTrue(getOutput().contains(expectedMessage));
  }
  
  /**
   * Adding node with invalid details.
   */
  @Test
  public void addingNodeWithInvalidDetails() {
    final String[] test = {
      "8",
      "15",
      "Ajinkya",
      "y",
      "phone",
      "\n",
      "n"
    };
    final String testString = String.join("\n",test);
    provideInput(testString);
    
    Application.main(new String[0]);
    final String expectedMessage =
        String.format(ExceptionsConstantsUtils.EMPTY_PARAMETER,
            StringConstantsUtils.VALUE);
    assertTrue(getOutput().contains(expectedMessage));
  }
  
  /**
   * Adding node with details.
   */
  @Test
  public void addingNodeWithDetails() {
    final String[] test = {
      "8",
      "15",
      "Ajinkya",
      "y",
      "phone",
      "8518076044",
      "n"
    };
    final String testString = String.join("\n",test);
    provideInput(testString);
    
    Application.main(new String[0]);
    final String expectedMessage = SuccessConstantsUtils.CREATED_NODE;
    assertTrue(getOutput().contains(expectedMessage));
  }
  
  /**
   * Adding node with character id.
   */
  @Test
  public void addingNodeWithCharacterId() {
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
  
  /**
   * Invalid menu input.
   */
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
  
  
  /**
   * Adding two nodes and creating dependency.
   */
  @Test
  public void addingTwoNodesAndCreatingDependency() {
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
      "y",
      "7",
      "1",
      "2"
    };
    final String testString = String.join("\n",test);
    provideInput(testString);
  
    Application.main(new String[0]);
    final String expectedMessage = SuccessConstantsUtils.CREATED_DEPENDENCY;
    assertTrue(getOutput().contains(expectedMessage));
  }
  
  /**
   * Adding two nodes and creating dependency but parent id not found.
   */
  @Test
  public void addingTwoNodesAndCreatingDependencyButParentIdNotFound() {
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
      "y",
      "7",
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
  
  /**
   * Adding two nodes and creating dependency but child id not found.
   */
  @Test
  public void addingTwoNodesAndCreatingDependencyButChildIdNotFound() {
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
      "y",
      "7",
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
  
  /**
   * Adding two nodes and creating dependency but parent child id same.
   */
  @Test
  public void addingTwoNodesAndCreatingDependencyButParentChildIdSame() {
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
      "y",
      "7",
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
  
  /**
   * Adding two nodes and creating cyclic dependency.
   */
  @Test
  public void addingTwoNodesAndCreatingCyclicDependency() {
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
      "y",
      "7",
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
  
  /**
   * Adding four nodes and creating cyclic dependency.
   */
  @Test
  public void addingFourNodesAndCreatingCyclicDependency() {
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
      "y",
      "7",
      "1",
      "2",
      "y",
      "8",
      "3",
      "Ajinkya3",
      "n",
      "y",
      "7",
      "2",
      "3",
      "y",
      "8",
      "4",
      "Ajinkya4",
      "n",
      "y",
      "7",
      "2",
      "4",
      "y",
      "7",
      "4",
      "1",
    };
    final String testString = String.join("\n",test);
    provideInput(testString);
    
    Application.main(new String[0]);
    final String expectedMessage =
        String.format(ExceptionsConstantsUtils.CYCLIC_DEPENDENCY, "4", "1");
    assertTrue(getOutput().contains(expectedMessage));
  }
  
  /**
   * Adding two nodes and creating dependency checking parent of node.
   */
  @Test
  public void addingTwoNodesAndCreatingDependencyCheckingParentOfNode() {
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
      "y",
      "7",
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
  
  /**
   * Adding two nodes and creating dependency checking children of node.
   */
  @Test
  public void addingTwoNodesAndCreatingDependencyCheckingChildrenOfNode() {
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
      "y",
      "7",
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
  
  /**
   * Adding two nodes and creating dependency checking ancestor of node.
   */
  @Test
  public void addingTwoNodesAndCreatingDependencyCheckingAncestorOfNode() {
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
      "y",
      "7",
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
  
  /**
   * Adding two nodes and creating dependency checking descendants of node.
   */
  @Test
  public void addingTwoNodesAndCreatingDependencyCheckingDescendantsOfNode() {
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
      "y",
      "7",
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
  
  /**
   * Adding node and then deleting node.
   */
  @Test
  public void addingNodeAndThenDeletingNode() {
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
    final String expectedMessage = SuccessConstantsUtils.DELETED_NODE;
    assertTrue(getOutput().contains(expectedMessage));
  }
  
  /**
   * Adding node and then deleting node but id not found.
   */
  @Test
  public void addingNodeAndThenDeletingNodeButIdNotFound() {
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
  
  /**
   * Adding two nodes and creating dependency then deleting dependency.
   */
  @Test
  public void addingTwoNodesAndCreatingDependencyThenDeletingDependency() {
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
      "y",
      "7",
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
  
  /**
   * Adding two nodes and creating dependency then deleting dependency but parent id not found.
   */
  @Test
  public void addingTwoNodesAndCreatingDependencyThenDeletingDependencyButParentIdNotFound() {
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
      "y",
      "7",
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
  
  /**
   * Adding two nodes then deleting dependency but no dependency.
   */
  @Test
  public void addingTwoNodesThenDeletingDependencyButNoDependency() {
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
      "y",
      "7",
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
  
  /**
   * Adding two nodes and creating dependency then deleting dependency but child id not found.
   */
  @Test
  public void addingTwoNodesAndCreatingDependencyThenDeletingDependencyButChildIdNotFound() {
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
      "y",
      "7",
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