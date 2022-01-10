package com.nuclei.assignment.service.itemparser;

import com.nuclei.assignment.constants.ExceptionsConstantsUtils;
import com.nuclei.assignment.enums.ItemType;
import com.nuclei.assignment.exception.CustomException;

import java.util.Locale;

/**
 * Item Parser Implementation containing method to parse different properties.
 */
public class ItemParserImpl implements ItemParser {
  
  @Override
  public String parseName(final String name) throws CustomException {
    if (name.isEmpty()) {
      throw new CustomException(ExceptionsConstantsUtils.INVALID_NAME);
    }
    return name;
  }
  
  @Override
  public double parsePrice(final String price) throws CustomException {
    final double parsedPrice;
    try {
      parsedPrice = Double.parseDouble(price);
      if (parsedPrice < 0) {
        throw new CustomException(ExceptionsConstantsUtils.INVALID_PRICE);
      }
    } catch (Exception exception) {
      throw new CustomException(ExceptionsConstantsUtils.INVALID_PRICE, exception);
    }
    return parsedPrice;
  }
  
  @Override
  public ItemType parseType(final String type) throws CustomException {
    final ItemType parsedItemType;
    try {
      parsedItemType = ItemType.valueOf(type.toUpperCase(Locale.ROOT));
    } catch (Exception exception) {
      throw new CustomException(ExceptionsConstantsUtils.INVALID_TYPE, exception);
    }
    return parsedItemType;
  }
  
  @Override
  public double parseQuantity(final String quantity) throws CustomException {
    final double parsedQuantity;
    try {
      parsedQuantity = Double.parseDouble(quantity);
      if (parsedQuantity < 0) {
        throw new CustomException(ExceptionsConstantsUtils.INVALID_QUANTITY);
      }
    } catch (Exception exception) {
      throw new CustomException(ExceptionsConstantsUtils.INVALID_QUANTITY, exception);
    }
    return parsedQuantity;
  }
}
