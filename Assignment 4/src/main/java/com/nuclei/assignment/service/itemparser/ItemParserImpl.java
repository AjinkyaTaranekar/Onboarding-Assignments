package com.nuclei.assignment.service.itemparser;

import com.nuclei.assignment.constants.ExceptionsConstantsUtils;
import com.nuclei.assignment.constants.StringConstantsUtils;
import com.nuclei.assignment.enums.ItemType;
import com.nuclei.assignment.exception.AttributeParseException;
import com.nuclei.assignment.exception.InputException;
import com.nuclei.assignment.service.itemvalidation.ItemValidation;
import com.nuclei.assignment.service.itemvalidation.ItemValidationImpl;
import com.nuclei.assignment.service.itemadder.ItemAdderImpl;
import java.util.Locale;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * Item Parser Implementation containing method to parse different properties.
 */
public class ItemParserImpl implements ItemParser {
  /**
   * The Item validation.
   */
  private final ItemValidation itemValidation;
  
  /**
   * The Logger.
   */
  private final Log logger = LogFactory.getLog(ItemAdderImpl.class);
  
  /**
   * Instantiates a new Item parser.
   */
  public ItemParserImpl() {
    itemValidation = new ItemValidationImpl();
  }
  
  @Override
  public String parseName(final String name) throws AttributeParseException {
    try {
      itemValidation.validateString(name);
    } catch (InputException exception) {
      logger.error(String.format(exception.getMessage(), StringConstantsUtils.NAME),
          exception);
      throw new AttributeParseException(String.format(exception.getMessage(),
          StringConstantsUtils.NAME), exception);
    }
    return name;
  }
  
  @Override
  public double parsePrice(final String price) throws AttributeParseException {
    try {
      itemValidation.validateNumeric(price);
    } catch (InputException exception) {
      logger.error(String.format(exception.getMessage(),
          StringConstantsUtils.PRICE, price), exception);
      throw new AttributeParseException(String.format(exception.getMessage(),
        StringConstantsUtils.PRICE, price), exception);
    }
    return Double.parseDouble(price);
  }
  
  @Override
  public ItemType parseType(final String type) throws AttributeParseException {
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
    try {
      itemValidation.validateNumeric(quantity);
    } catch (InputException exception) {
      logger.error(String.format(exception.getMessage(),
          StringConstantsUtils.QUANTITY, quantity), exception);
      throw new AttributeParseException(String.format(exception.getMessage(),
          StringConstantsUtils.QUANTITY, quantity), exception);
    }
    return Double.parseDouble(quantity);
  }
  
}
