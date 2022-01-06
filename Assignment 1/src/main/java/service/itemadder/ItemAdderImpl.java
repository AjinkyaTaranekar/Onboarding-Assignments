package service.itemadder;

import constants.ExceptionsConstants;
import constants.FlagsConstants;
import constants.StringConstants;
import entity.Item;
import enums.ItemType;
import exception.CustomException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import service.itemparser.ItemParser;
import service.itemparser.ItemParserImpl;
import service.tax.ItemTax;
import service.tax.ItemTaxImpl;

/**
 * Item Adder Implementation containing method to add items.
 * **/
public class ItemAdderImpl implements ItemAdder {
  public ItemAdderImpl() {}
  
  @Override
  public void inputItemsFromUserInterface(String[] rawData) {
    for (final String info : StringConstants.ITEM_DETAILS_INFO) {
      System.out.println(info);
    }
    
    int itemTypeCount = 1;
    for (final ItemType type : ItemType.values()) {
      System.out.printf(StringConstants.ITEM_TYPES, itemTypeCount, type);
      itemTypeCount++;
    }
    System.out.println(StringConstants.DIVIDER);
    
    final List<Item> items = addItemsToList(rawData);
    outputItemsWithTaxToUser(items);
  }
  
  @Override
  public void outputItemsWithTaxToUser(List<Item> items) {
    System.out.println(StringConstants.DIVIDER);
    System.out.println(StringConstants.SHOW_ITEM_LIST);
    int itemCount = 0;
    for (final Item item : items) {
      itemCount += 1;
      System.out.printf(StringConstants.SHOW_ITEM_LIST_INFO,
          item.getType(), itemCount, item.getName(), item.getPrice(),
          item.getQuantity(), item.getSalesTax(), item.getFinalPrice());
    }
  }
  
  private List<Item> addItemsToList(String[] rawData) {
    final ArrayList<Item> items = new ArrayList<>();
    String addMoreToItem = StringConstants.CONFIRMATION;
    createItemsPrompt(addMoreToItem, items, rawData);
    return items;
  }
  
  private String addMoreItemsPrompt(Scanner scanner) {
    System.out.println(StringConstants.DIVIDER);
    System.out.println(StringConstants.ADD_MORE_ITEMS_QUERY);
    return scanner.next().toLowerCase();
  }
  
  private void createItemsPrompt(String addMoreToItems, List<Item> items,
                                 String[] rawData) {
    if (!addMoreToItems.equals(StringConstants.CONFIRMATION)) {
      return;
    }
    final Scanner scanner = new Scanner(System.in);
    try {
      String[] input = rawData;
      if (rawData.length == 0) {
        System.out.println(StringConstants.INSERT_ITEM_DETAILS);
        input = scanner.nextLine().split(" ");
      }
      final Item item = createItem(input);
      items.add(item);
    } catch (Exception exception) {
      System.out.println(exception.getMessage());
    } finally {
      addMoreToItems = addMoreItemsPrompt(scanner);
    }
    createItemsPrompt(addMoreToItems, items, new String[0]);
    scanner.close();
  }
  
  /**
   * Create Item method converting raw data to Item.
   * **/
  public Item createItem(String[] itemProperties) throws CustomException {
    
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
  
  private void setTaxForTheNewItem(Item item) throws CustomException {
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
        throw new CustomException(ExceptionsConstants.INVALID_TYPE);
    }
    item.setFinalPrice(item.getSalesTax() + item.getPrice());
  }
  
}
