package com.nuclei.assignment.service.itemadder;

import com.nuclei.assignment.constants.ExceptionsConstants;
import com.nuclei.assignment.constants.FlagsConstants;
import com.nuclei.assignment.constants.StringConstants;
import com.nuclei.assignment.entity.Item;
import com.nuclei.assignment.enums.ItemType;
import com.nuclei.assignment.exception.CustomException;
import com.nuclei.assignment.service.itemparser.ItemParser;
import com.nuclei.assignment.service.itemparser.ItemParserImpl;
import com.nuclei.assignment.service.tax.ItemTax;
import com.nuclei.assignment.service.tax.ItemTaxImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

/**
 * Item Adder Implementation containing method to add items.
 * **/
public class ItemAdderImpl implements ItemAdder {
  
  @Override
  public void inputItemsFromUserInterface(final String... rawData) {
    try (Scanner scanner = new Scanner(System.in)) {
      for (final String info : StringConstants.ITEM_DETAILS_INFO) {
        System.out.println(info);
      }
      int itemTypeCount = 1;
      for (final ItemType type : ItemType.values()) {
        System.out.printf(StringConstants.ITEM_TYPES, itemTypeCount, type);
        itemTypeCount++;
      }
      System.out.println(StringConstants.DIVIDER);
      final List<Item> items = addItemsToList(scanner, rawData);
      outputItemsWithTaxToUser(items);
    } catch (Exception exception) {
      System.out.println(exception.getMessage());
    }
  }
  
  @Override
  public void outputItemsWithTaxToUser(final List<Item> items) throws CustomException {
    System.out.println(StringConstants.DIVIDER);
    if (items.isEmpty()) {
      throw new CustomException(ExceptionsConstants.NO_INPUT);
    }
    System.out.println(StringConstants.SHOW_ITEM_LIST);
    int itemCount = 0;
    for (final Item item : items) {
      itemCount += 1;
      System.out.printf(StringConstants.SHOW_ITEM_LIST_INFO,
          item.getType(), itemCount, item.getName(), item.getPrice(),
          item.getQuantity(), item.getSalesTax(), item.getFinalPrice());
    }
  }
  
  private List<Item> addItemsToList(final Scanner scanner,
                                    final String... rawData) {
    final ArrayList<Item> items = new ArrayList<>();
    String addMoreToItems = StringConstants.CONFIRMATION;
    boolean rawDataAvailable = rawData.length != 0;
    while (addMoreToItems.equals(StringConstants.CONFIRMATION)) {
      try {
        String[] input = rawData;
        if (!rawDataAvailable) {
          System.out.println(StringConstants.INSERT_ITEM_DETAILS);
          input = scanner.nextLine().split(" ");
        }
        final Item item = createItem(input);
        items.add(item);
      } catch (Exception exception) {
        System.out.println(exception.getMessage());
      } finally {
        rawDataAvailable = false;
        System.out.println(StringConstants.DIVIDER);
        System.out.println(StringConstants.ADD_MORE_ITEMS_QUERY);
  
        addMoreToItems = scanner.nextLine().toLowerCase(Locale.ROOT);
      }
    }
    return items;
  }
  
  /**
   * Create Item method converting raw data to Item.
   * **/
  public Item createItem(final String... itemProperties) throws CustomException {
  
    validateItemProperties(itemProperties);
  
    final ItemParser parser = new ItemParserImpl();
    String itemName = "";
    double itemPrice = 0;
    double itemQuantity = 0;
    ItemType itemType = ItemType.RAW;
    
    for (int index = 0; index < itemProperties.length; index += 2) {
      if (index + 1 > itemProperties.length) {
        throw new CustomException(ExceptionsConstants.INVALID_INPUT);
      }
      switch (itemProperties[index]) {
        case FlagsConstants.NAME_FLAG:
          itemName = parser.parseName(itemProperties[index + 1]);
          break;
        case FlagsConstants.PRICE_FLAG:
          itemPrice = parser.parsePrice(itemProperties[index + 1]);
          break;
        case FlagsConstants.QUANTITY_FLAG:
          itemQuantity = parser.parseQuantity(itemProperties[index + 1]);
          break;
        case FlagsConstants.TYPE_FLAG:
          itemType = parser.parseType(itemProperties[index + 1]);
          break;
        default:
          throw new CustomException(ExceptionsConstants.INVALID_INPUT);
      }
    }
    final Item item = new Item(itemName, itemPrice, itemQuantity, itemType);
    
    setTaxForTheNewItem(item);
    return item;
  }
  
  private void validateItemProperties(final String... itemProperties) throws CustomException {
    // itemProperties shouldn't be less than FlagsConstants.MUST_ARGUMENTS
    if (itemProperties.length < FlagsConstants.MUST_ARGUMENTS) {
      throw new CustomException(ExceptionsConstants.INVALID_INPUT);
    }
    
    // first property should be FlagsConstants.NAME_FLAG
    if (!FlagsConstants.NAME_FLAG.equals(itemProperties[0])) {
      throw new CustomException(ExceptionsConstants.INVALID_INPUT);
    }
    
    // properties should have FlagsConstants.TYPE_FLAG
    if (Arrays.stream(itemProperties).noneMatch(FlagsConstants.TYPE_FLAG::equals)) {
      throw new CustomException(ExceptionsConstants.INVALID_INPUT);
    }
  }
  
  private void setTaxForTheNewItem(final Item item) throws CustomException {
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
