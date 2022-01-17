package com.nuclei.assignment.service.itemadder;

import com.nuclei.assignment.connections.DatabaseOperations;
import com.nuclei.assignment.connections.DatabaseOperationsImpl;
import com.nuclei.assignment.constants.ExceptionsConstantsUtils;
import com.nuclei.assignment.constants.FlagsConstantsUtils;
import com.nuclei.assignment.constants.StringConstantsUtils;
import com.nuclei.assignment.entity.ItemEntity;
import com.nuclei.assignment.enums.ItemType;
import com.nuclei.assignment.exception.AttributeParseException;
import com.nuclei.assignment.exception.DatabaseException;
import com.nuclei.assignment.exception.InputException;
import com.nuclei.assignment.service.itemparser.ItemParser;
import com.nuclei.assignment.service.itemparser.ItemParserImpl;
import com.nuclei.assignment.service.tax.ItemTax;
import com.nuclei.assignment.service.tax.ItemTaxImpl;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Item Adder Implementation containing method to add items.
 */
public class ItemAdderImpl implements ItemAdder {
  
  /**
   * The Logger.
   */
  private final Log logger = LogFactory.getLog(ItemAdderImpl.class);
  
  private final DatabaseOperations databaseOperations;
  
  /**
   * Instantiates a new Item adder.
   *
   * @throws DatabaseException the database exception
   */
  public ItemAdderImpl() throws DatabaseException {
    databaseOperations = new DatabaseOperationsImpl();
  }
  
  @Override
  public void inputItemsFromUserInterface(final String... rawData) {
    logger.info("New User Session");
    try (Scanner scanner = new Scanner(System.in)) {
      for (final String info : StringConstantsUtils.ITEM_DETAILS_INFO) {
        System.out.println(info);
      }
      int itemTypeCount = 1;
      for (final ItemType type :ItemType.values()) {
        System.out.printf(StringConstantsUtils.ITEM_TYPES, itemTypeCount, type);
        itemTypeCount++;
      }
      System.out.println(StringConstantsUtils.DIVIDER);
      addItemsToList(scanner, rawData);
      outputItemsWithTaxToUser();
      logger.info("User Session ended");
    } catch (Exception exception) {
      System.out.println(exception.getMessage());
    }
  }
  
  @Override
  public void outputItemsWithTaxToUser()
      throws InputException, AttributeParseException, DatabaseException {
    final List<ItemEntity> items = databaseOperations.getAllItems();
    System.out.println(StringConstantsUtils.DIVIDER);
    if (items.isEmpty()) {
      logger.error(ExceptionsConstantsUtils.NO_INPUT);
      throw new InputException(ExceptionsConstantsUtils.NO_INPUT);
    }
    logger.info(String.format("Received items: \n%s", items));
    System.out.println(StringConstantsUtils.SHOW_ITEM_LIST);
    int itemCount = 0;
    for (final ItemEntity item : items) {
      itemCount += 1;
      System.out.printf(StringConstantsUtils.SHOW_ITEM_LIST_INFO,
          itemCount, item.getType(), item.getName(), item.getPrice(),
          item.getQuantity(), item.getSalesTax(), item.getFinalPrice());
    }
  }
  
  private void addItemsToList(final Scanner scanner, final String... rawData) {
    String addMoreToItems = StringConstantsUtils.CONFIRMATION;
    boolean rawDataAvailable = rawData.length != 0;
    while (addMoreToItems.equals(StringConstantsUtils.CONFIRMATION)) {
      try {
        String[] input = rawData;
        if (!rawDataAvailable) {
          System.out.println(StringConstantsUtils.INSERT_ITEM_DETAILS);
          input = scanner.nextLine().split(" ");
        }
        final ItemEntity item = createItem(input);
        logger.info(String.format("Created %s", item));
      } catch (Exception exception) {
        System.out.println(exception.getMessage());
      } finally {
        rawDataAvailable = false;
        System.out.println(StringConstantsUtils.DIVIDER);
        System.out.println(StringConstantsUtils.ADD_MORE_ITEMS_QUERY);
  
        addMoreToItems = scanner.nextLine().toLowerCase(Locale.ROOT);
      }
    }
  }
  
  private ItemEntity createItem(final String... itemProperties)
      throws InputException, AttributeParseException, DatabaseException {
  
    validateItemProperties(itemProperties);
    final Map<String, String> properties = new ConcurrentHashMap<>();
    
    for (int index = 0; index < itemProperties.length; index += 2) {
      switch (itemProperties[index]) {
        case FlagsConstantsUtils.NAME_FLAG:
          properties.put(FlagsConstantsUtils.NAME_FLAG,
              itemProperties[index + 1]);
          break;
        case FlagsConstantsUtils.PRICE_FLAG:
          properties.put(FlagsConstantsUtils.PRICE_FLAG,
              itemProperties[index + 1]);
          break;
        case FlagsConstantsUtils.QUANTITY_FLAG:
          properties.put(FlagsConstantsUtils.QUANTITY_FLAG,
              itemProperties[index + 1]);
          break;
        case FlagsConstantsUtils.TYPE_FLAG:
          properties.put(FlagsConstantsUtils.TYPE_FLAG,
              itemProperties[index + 1]);
          break;
        default:
          throw new InputException(
              String.format(ExceptionsConstantsUtils.INVALID_INPUT,
                itemProperties[index]));
      }
    }
    
    final ItemEntity item = parseItemPropertiesMapEntries(properties);
    databaseOperations.saveItem(item);
    setTaxForTheNewItem(item);
    return item;
  }
  
  private ItemEntity parseItemPropertiesMapEntries(final Map<String, String> properties)
      throws AttributeParseException {
    for (final String property : properties.keySet()) {
      final String value = properties.get(property);
      if (value.equals(FlagsConstantsUtils.NAME_FLAG)
          || value.equals(FlagsConstantsUtils.PRICE_FLAG)
          || value.equals(FlagsConstantsUtils.TYPE_FLAG)
          || value.equals(FlagsConstantsUtils.QUANTITY_FLAG)) {
        throw new AttributeParseException(
          String.format(ExceptionsConstantsUtils.INVALID_INPUT_NO_DATA_FOR_FLAG,
            property));
      }
    }
  
    final ItemParser parser = new ItemParserImpl();
    final String itemName =
        parser.parseName(properties.getOrDefault(FlagsConstantsUtils.NAME_FLAG,
        ""));
    final ItemType itemType =
        parser.parseType(properties.getOrDefault(FlagsConstantsUtils.TYPE_FLAG,
        ""));
    final double itemPrice =
        parser.parsePrice(properties.getOrDefault(FlagsConstantsUtils.PRICE_FLAG,
        "0"));
    final double itemQuantity =
        parser.parseQuantity(properties.getOrDefault(FlagsConstantsUtils.QUANTITY_FLAG,
        "0"));
    
    return new ItemEntity(itemName, itemPrice, itemQuantity, itemType);
    
  }

  private void validateItemProperties(final String... itemProperties) throws InputException {
    // itemProperties shouldn't be less than FlagsConstantsUtils.MUST_ARGUMENTS
    if (itemProperties.length < FlagsConstantsUtils.MUST_ARGUMENTS) {
      logger.error(String.format(ExceptionsConstantsUtils.INVALID_INPUT_LENGTH,
          Arrays.toString(itemProperties)), new Throwable().fillInStackTrace());
      throw new InputException(
        String.format(ExceptionsConstantsUtils.INVALID_INPUT_LENGTH,
          Arrays.toString(itemProperties)));
    }
    
    // first property should be FlagsConstantsUtils.NAME_FLAG
    if (!FlagsConstantsUtils.NAME_FLAG.equals(itemProperties[0])) {
      logger.error(
          String.format(ExceptionsConstantsUtils.INVALID_INPUT_NAME_NOT_AT_START,
          Arrays.toString(itemProperties)), new Throwable().fillInStackTrace());
      throw new InputException(
          String.format(ExceptionsConstantsUtils.INVALID_INPUT_NAME_NOT_AT_START,
          Arrays.toString(itemProperties)));
    }
    
    // properties should have FlagsConstantsUtils.TYPE_FLAG
    if (Arrays.stream(itemProperties).noneMatch(FlagsConstantsUtils.TYPE_FLAG::equals)) {
      logger.error(String.format(ExceptionsConstantsUtils.INVALID_INPUT_NO_TYPE,
          Arrays.toString(itemProperties)), new Throwable().fillInStackTrace());
      throw new InputException(String.format(ExceptionsConstantsUtils.INVALID_INPUT_NO_TYPE,
        Arrays.toString(itemProperties)));
    }
  
    // itemProperties should be a multiple of 2 such that each key has value
    if (itemProperties.length % 2 != 0) {
      logger.error(String.format(ExceptionsConstantsUtils.INVALID_INPUT_NO_DATA_FOR_FLAG,
          Arrays.toString(itemProperties)), new Throwable().fillInStackTrace());
      throw new InputException(
        String.format(ExceptionsConstantsUtils.INVALID_INPUT_NO_DATA_FOR_FLAG,
          Arrays.toString(itemProperties)));
    }
  
  }
  
  private void setTaxForTheNewItem(final ItemEntity item) {
    final ItemTax itemTaxService = new ItemTaxImpl();
    switch (item.getType()) {
      case RAW:
        item.setSalesTax(itemTaxService.getTaxForRawItem(item.getPrice()));
        break;
      case MANUFACTURED:
        item.setSalesTax(itemTaxService.getTaxForManufacturedItem(item.getPrice()));
        break;
      case IMPORTED:
        item.setSalesTax(itemTaxService.getTaxForImportedItem(item.getPrice()));
        break;
      default:
    }
    item.setFinalPrice(item.getSalesTax() + item.getPrice());
  }
  
}