package com.nuclei.assignment.service.menuoptions;

import com.nuclei.assignment.constants.ExceptionsConstantsUtils;
import com.nuclei.assignment.constants.StringConstantsUtils;
import com.nuclei.assignment.entity.NodeEntity;
import com.nuclei.assignment.exception.CustomException;
import com.nuclei.assignment.service.graphoperations.GraphOperations;
import com.nuclei.assignment.service.graphoperations.GraphOperationsImpl;
import com.nuclei.assignment.service.inputparser.InputParser;
import com.nuclei.assignment.service.inputparser.InputParserImpl;

import java.util.Locale;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Menu Option implementation for different types of access.
 */
public class MenuOptionsImpl implements MenuOptions {
  /**
   * The Graph operations.
   */
  private final GraphOperations graphOperations;
  
  /**
   * The Parser.
   */
  private final InputParser parser;
  
  /**
   * The Logger.
   */
  private final Log logger = LogFactory.getLog(MenuOptionsImpl.class);
  
  /**
   * Menu Option constructor.
   */
  public MenuOptionsImpl() {
    graphOperations = new GraphOperationsImpl();
    parser = new InputParserImpl();
  }
  
  @Override
  public void menuInterface() {
    try (Scanner scanner = new Scanner(System.in)) {
      String addMoreNodes = StringConstantsUtils.CONFIRMATION;
      while (addMoreNodes.equals(StringConstantsUtils.CONFIRMATION)) {
        for (final String info : StringConstantsUtils.MENU_DETAILS_INFO) {
          System.out.println(info);
        }
        try {
          chooseMenuOption(scanner);
        } catch (Exception exception) {
          System.out.println(exception.getMessage());
        } finally {
          System.out.println(StringConstantsUtils.DIVIDER);
          System.out.print(StringConstantsUtils.CONTINUE_MORE_OPERATIONS);
          addMoreNodes = scanner.nextLine().toLowerCase(Locale.ROOT);
        }
      }
    } catch (Exception exception) {
      System.out.println(exception.getMessage());
    }
  }
  
  // Suppressing warning of
  // The method 'chooseMenuOption(Scanner)' has a cyclomatic complexity of 10.
  // Since the menu has 8 options, so it can't be reduced further.
  @SuppressWarnings("PMD.CyclomaticComplexity")
  private void chooseMenuOption(final Scanner scanner) throws CustomException {
    final int menuOption = Integer.parseInt(scanner.nextLine());
    switch (menuOption) {
      case 1:
        getImmediateParents(scanner);
        break;
      case 2:
        getImmediateChildren(scanner);
        break;
      case 3:
        getAllAncestors(scanner);
        break;
      case 4:
        getAllDescendants(scanner);
        break;
      case 5:
        deleteDependency(scanner);
        break;
      case 6:
        deleteNodeById(scanner);
        break;
      case 7:
        createDependency(scanner);
        break;
      case 8:
        createNode(scanner);
        break;
      default:
        logger.error(String.format(ExceptionsConstantsUtils.INVALID_INPUT, menuOption));
        throw new CustomException(String.format(ExceptionsConstantsUtils.INVALID_INPUT,
            menuOption));
    }
  }
  
  private void createNode(final Scanner scanner) throws CustomException {
    System.out.println(StringConstantsUtils.ADD_NODE_ID);
    final int id = parser.parseId(scanner.nextLine());
    System.out.println(StringConstantsUtils.ADD_NODE_NAME);
    final String name = parser.parseString(scanner.nextLine(),
        StringConstantsUtils.NAME);
    final Map<String, String> metadata = new ConcurrentHashMap<>();
    
    System.out.println(StringConstantsUtils.ADD_NODE_DETAILS_QUERY);
    String addMetaData = parser.parseString(scanner.nextLine(),
        StringConstantsUtils.QUERY);
    while (addMetaData.equals(StringConstantsUtils.CONFIRMATION)) {
      System.out.println(StringConstantsUtils.ENTER_KEY);
      final String key = parser.parseString(scanner.nextLine(),
          StringConstantsUtils.KEY);
      
      System.out.printf((StringConstantsUtils.ENTER_VALUE) + "%n", key);
      final String value = parser.parseString(scanner.nextLine(),
          StringConstantsUtils.VALUE);
      metadata.put(key, value);
      
      System.out.println(StringConstantsUtils.ADD_NODE_DETAILS_QUERY);
      addMetaData = parser.parseString(scanner.nextLine(),
          StringConstantsUtils.QUERY);
    }
    final NodeEntity node = new NodeEntity(id, name, metadata);
    graphOperations.createNode(node);
  }
  
  private void createDependency(final Scanner scanner) throws CustomException {
    System.out.println(StringConstantsUtils.ADD_PARENT_ID);
    final int parentId = parser.parseId(scanner.nextLine());
    System.out.println(StringConstantsUtils.ADD_CHILD_ID);
    final int childId = parser.parseId(scanner.nextLine());
    graphOperations.createDependency(parentId, childId);
  }
  
  private void getImmediateParents(final Scanner scanner) throws CustomException {
    System.out.println(StringConstantsUtils.ADD_NODE_ID);
    final int id = parser.parseId(scanner.nextLine());
    final Set<Integer> parents = graphOperations.getImmediateParents(id);
    System.out.println(parents);
  }
  
  private void getImmediateChildren(final Scanner scanner) throws CustomException {
    System.out.println(StringConstantsUtils.ADD_NODE_ID);
    final int id = parser.parseId(scanner.nextLine());
    final Set<Integer> children = graphOperations.getImmediateChildren(id);
    System.out.println(children);
  }
  
  private void getAllAncestors(final Scanner scanner) throws CustomException {
    System.out.println(StringConstantsUtils.ADD_NODE_ID);
    final int id = parser.parseId(scanner.nextLine());
    final Set<Integer> ancestors = graphOperations.getAllAncestors(id);
    System.out.println(ancestors);
  }
  
  private void getAllDescendants(final Scanner scanner) throws CustomException {
    System.out.println(StringConstantsUtils.ADD_NODE_ID);
    final int id = parser.parseId(scanner.nextLine());
    final Set<Integer> descendants = graphOperations.getAllDescendants(id);
    System.out.println(descendants);
  }
  
  private void deleteNodeById(final Scanner scanner) throws CustomException {
    System.out.println(StringConstantsUtils.ADD_NODE_ID);
    final int id = parser.parseId(scanner.nextLine());
    graphOperations.deleteNodeById(id);
  }
  
  private void deleteDependency(final Scanner scanner) throws CustomException {
    System.out.println(StringConstantsUtils.ADD_PARENT_ID);
    final int parentId = parser.parseId(scanner.nextLine());
    System.out.println(StringConstantsUtils.ADD_CHILD_ID);
    final int childId = parser.parseId(scanner.nextLine());
    graphOperations.deleteDependency(parentId, childId);
  }
  
}
