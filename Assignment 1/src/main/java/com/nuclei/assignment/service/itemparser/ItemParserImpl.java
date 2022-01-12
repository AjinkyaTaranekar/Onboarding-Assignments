package com.nuclei.assignment.service.itemparser;

import com.nuclei.assignment.constants.ExceptionsConstantsUtils;
import com.nuclei.assignment.constants.FlagsConstantsUtils;
import com.nuclei.assignment.enums.ItemType;
import com.nuclei.assignment.exception.AttributeParseException;
import com.nuclei.assignment.service.itemadder.ItemAdderImpl;
import java.util.Locale;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * Item Parser Implementation containing method to parse different properties.
 */
public class ItemParserImpl implements ItemParser {
  
  /**
   * The Logger.
   */
  private final Log logger = LogFactory.getLog(ItemAdderImpl.class);
  
  @Override
  public String parseName(final String name) throws AttributeParseException {
    validateData(name, FlagsConstantsUtils.NAME_FLAG);
    if (name.isEmpty()) {
      logger.error(ExceptionsConstantsUtils.EMPTY_NAME, new Throwable().fillInStackTrace());
      throw new AttributeParseException(ExceptionsConstantsUtils.EMPTY_NAME);
    }
    return name;
  }
  
  @Override
  public double parsePrice(final String price) throws AttributeParseException {
    validateData(price,  FlagsConstantsUtils.PRICE_FLAG);
    final double parsedPrice;
    try {
      parsedPrice = Double.parseDouble(price);
      if (parsedPrice < 0) {
        logger.error(String.format(ExceptionsConstantsUtils.NEGATIVE_PRICE, price),
            new Throwable().fillInStackTrace());
        throw new AttributeParseException(
            String.format(ExceptionsConstantsUtils.NEGATIVE_PRICE, price));
      }
    } catch (NumberFormatException exception) {
      logger.error(String.format(ExceptionsConstantsUtils.INVALID_PRICE,
          price), new Throwable().fillInStackTrace());
      throw new AttributeParseException(
          String.format(ExceptionsConstantsUtils.INVALID_PRICE, price), exception);
    }
    return parsedPrice;
  }
  
  @Override
  public ItemType parseType(final String type) throws AttributeParseException {
    validateData(type, FlagsConstantsUtils.TYPE_FLAG);
    final ItemType parsedItemType;
    try {
      parsedItemType = ItemType.valueOf(type.toUpperCase(Locale.ROOT));
    } catch (IllegalArgumentException exception) {
      logger.error(
          String.format(ExceptionsConstantsUtils.INVALID_TYPE, type),
          new Throwable().fillInStackTrace());
      throw new AttributeParseException(
          String.format(ExceptionsConstantsUtils.INVALID_TYPE, type), exception);
    }
    return parsedItemType;
  }
  
  @Override
  public double parseQuantity(final String quantity) throws AttributeParseException {
    validateData(quantity, FlagsConstantsUtils.QUANTITY_FLAG);
    final double parsedQuantity;
    try {
      parsedQuantity = Double.parseDouble(quantity);
      if (parsedQuantity < 0) {
        logger.error(
            String.format(ExceptionsConstantsUtils.NEGATIVE_QUANTITY, quantity),
            new Throwable().fillInStackTrace());
        throw new AttributeParseException(
            String.format(ExceptionsConstantsUtils.NEGATIVE_QUANTITY, quantity));
      }
    } catch (NumberFormatException exception) {
      logger.error(
          String.format(ExceptionsConstantsUtils.INVALID_QUANTITY,
          quantity), new Throwable().fillInStackTrace());
      throw new AttributeParseException(
          String.format(ExceptionsConstantsUtils.INVALID_QUANTITY, quantity), exception);
    }
    return parsedQuantity;
  }
  
  private void validateData(String data, String checkedOnAttributeName)
      throws AttributeParseException {
    checkDataIsNotNull(data, checkedOnAttributeName);
    checkDataIsNotFlag(data, checkedOnAttributeName);
  }
  
  private void checkDataIsNotFlag(String data, String checkedOnAttributeName)
      throws AttributeParseException {
    if (data.equals(FlagsConstantsUtils.NAME_FLAG)
        || data.equals(FlagsConstantsUtils.TYPE_FLAG)
        || data.equals(FlagsConstantsUtils.QUANTITY_FLAG)
        || data.equals(FlagsConstantsUtils.PRICE_FLAG)) {
      logger.error(String.format(ExceptionsConstantsUtils.INVALID_INPUT_NO_DATA_FOR_FLAG,
          checkedOnAttributeName), new Throwable().fillInStackTrace());
      throw new AttributeParseException(
          String.format(ExceptionsConstantsUtils.INVALID_INPUT_NO_DATA_FOR_FLAG,
            checkedOnAttributeName));
    }
  }
  
  private void checkDataIsNotNull(String data, String checkedOnAttributeName)
      throws AttributeParseException {
    if (data == null) {
      logger.error(String.format(ExceptionsConstantsUtils.INVALID_INPUT_DATA_NOT_NULL,
          checkedOnAttributeName), new Throwable().fillInStackTrace());
      throw new AttributeParseException(
          String.format(ExceptionsConstantsUtils.INVALID_INPUT_DATA_NOT_NULL,
          checkedOnAttributeName));
    }
  }
}
