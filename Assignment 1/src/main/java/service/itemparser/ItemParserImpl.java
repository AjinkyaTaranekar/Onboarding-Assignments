package service.itemparser;

import constants.ExceptionsConstants;
import enums.ItemType;
import exception.CustomException;

/**
 * Item Parser Implementation containing method to parse different properties.
 * **/
public class ItemParserImpl implements ItemParser {
  public ItemParserImpl() {}
  
  @Override
  public String parseName(String name) throws CustomException {
    if (name.isEmpty()) {
      throw new CustomException(ExceptionsConstants.INVALID_NAME);
    }
    return name;
  }
  
  @Override
  public double parsePrice(String price) throws CustomException {
    double parsedPrice;
    try {
      parsedPrice = Double.parseDouble(price);
      if (parsedPrice < 0) {
        throw new CustomException(ExceptionsConstants.INVALID_PRICE);
      }
    } catch (Exception exception) {
      throw new CustomException(ExceptionsConstants.INVALID_PRICE);
    }
    return parsedPrice;
  }
  
  @Override
  public ItemType parseType(String type) throws CustomException {
    ItemType parsedItemType;
    try {
      parsedItemType = ItemType.valueOf(type.toUpperCase());
    } catch (Exception exception) {
      throw new CustomException(ExceptionsConstants.INVALID_TYPE);
    }
    return parsedItemType;
  }
  
  @Override
  public double parseQuantity(String quantity) throws CustomException {
    double parsedQuantity;
    try {
      parsedQuantity = Double.parseDouble(quantity);
      if (parsedQuantity < 0) {
        throw new CustomException(ExceptionsConstants.INVALID_QUANTITY);
      }
    } catch (Exception exception) {
      throw new CustomException(ExceptionsConstants.INVALID_QUANTITY);
    }
    return parsedQuantity;
  }
}
