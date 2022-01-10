package com.nuclei.assignment.service.itemadder;

import com.nuclei.assignment.constants.ExceptionsConstantsUtils;
import com.nuclei.assignment.constants.FlagsConstantsUtils;
import com.nuclei.assignment.constants.StringConstantsUtils;
import com.nuclei.assignment.entity.ItemEntity;
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
 */
public class ItemAdderImpl implements ItemAdder {
  
  @Override
  public void inputItemsFromUserInterface(final String... rawData) {
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
      final List<ItemEntity> items = addItemsToList(scanner, rawData);
      outputItemsWithTaxToUser(items);
    } catch (Exception exception) {
      System.out.println(exception.getMessage());
    }
  }
  
  @Override
  public void outputItemsWithTaxToUser(final List<ItemEntity> items) throws CustomException {
    System.out.println(StringConstantsUtils.DIVIDER);
    if (items.isEmpty()) {
      throw new CustomException(ExceptionsConstantsUtils.NO_INPUT);
    }
    System.out.println(StringConstantsUtils.SHOW_ITEM_LIST);
    int itemCount = 0;
    for (final ItemEntity item : items) {
      itemCount += 1;
      System.out.printf(StringConstantsUtils.SHOW_ITEM_LIST_INFO,
          item.getType(), itemCount, item.getName(), item.getPrice(),
          item.getQuantity(), item.getSalesTax(), item.getFinalPrice());
    }
  }
  
  private List<ItemEntity> addItemsToList(final Scanner scanner,
                                    final String... rawData) {
    final ArrayList<ItemEntity> items = new ArrayList<>();
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
        items.add(item);
      } catch (Exception exception) {
        System.out.println(exception.getMessage());
      } finally {
        rawDataAvailable = false;
        System.out.println(StringConstantsUtils.DIVIDER);
        System.out.println(StringConstantsUtils.ADD_MORE_ITEMS_QUERY);
  
        addMoreToItems = scanner.nextLine().toLowerCase(Locale.ROOT);
      }
    }
    return items;
  }
  
  /**
   * Create Item method converting raw data to Item.
   *
   * @param itemProperties the item properties
   * @return the item
   * @throws CustomException the custom exception
   */
  public ItemEntity createItem(final String... itemProperties) throws CustomException {
  
    validateItemProperties(itemProperties);
  
    final ItemParser parser = new ItemParserImpl();
    String itemName = "";
    double itemPrice = 0;
    double itemQuantity = 0;
    ItemType itemType = ItemType.RAW;
    
    for (int index = 0; index < itemProperties.length; index += 2) {
      if (index + 1 > itemProperties.length) {
        throw new CustomException(ExceptionsConstantsUtils.INVALID_INPUT);
      }
      switch (itemProperties[index]) {
        case FlagsConstantsUtils.NAME_FLAG:
          itemName = parser.parseName(itemProperties[index + 1]);
          break;
        case FlagsConstantsUtils.PRICE_FLAG:
          itemPrice = parser.parsePrice(itemProperties[index + 1]);
          break;
        case FlagsConstantsUtils.QUANTITY_FLAG:
          itemQuantity = parser.parseQuantity(itemProperties[index + 1]);
          break;
        case FlagsConstantsUtils.TYPE_FLAG:
          itemType = parser.parseType(itemProperties[index + 1]);
          break;
        default:
          throw new CustomException(ExceptionsConstantsUtils.INVALID_INPUT);
      }
    }
    final ItemEntity item = new ItemEntity(itemName, itemPrice, itemQuantity, itemType);
    
    setTaxForTheNewItem(item);
    return item;
  }
  
  private void validateItemProperties(final String... itemProperties) throws CustomException {
    // itemProperties shouldn't be less than FlagsConstantsUtils.MUST_ARGUMENTS
    if (itemProperties.length < FlagsConstantsUtils.MUST_ARGUMENTS) {
      throw new CustomException(ExceptionsConstantsUtils.INVALID_INPUT);
    }
    
    // first property should be FlagsConstantsUtils.NAME_FLAG
    if (!FlagsConstantsUtils.NAME_FLAG.equals(itemProperties[0])) {
      throw new CustomException(ExceptionsConstantsUtils.INVALID_INPUT);
    }
    
    // properties should have FlagsConstantsUtils.TYPE_FLAG
    if (Arrays.stream(itemProperties).noneMatch(FlagsConstantsUtils.TYPE_FLAG::equals)) {
      throw new CustomException(ExceptionsConstantsUtils.INVALID_INPUT);
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
