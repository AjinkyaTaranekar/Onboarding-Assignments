package com.nuclei.assignment.service.displayitem;

import com.nuclei.assignment.constants.DatabaseConstantsUtils;
import com.nuclei.assignment.constants.StringConstantsUtils;
import com.nuclei.assignment.entity.ItemEntity;
import com.nuclei.assignment.enums.ItemType;
import com.nuclei.assignment.exception.AttributeParseException;
import com.nuclei.assignment.exception.DatabaseException;
import com.nuclei.assignment.service.itemparser.ItemParser;
import com.nuclei.assignment.service.itemparser.ItemParserImpl;
import com.nuclei.assignment.service.tax.ItemTax;
import com.nuclei.assignment.service.tax.ItemTaxImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * The type Display items synchronous threads.
 */
public class DisplayItemsSynchronousThreadsImpl implements DisplayItemsSynchronousThreads {
  
  /**
   * The Logger.
   */
  private final Log logger = LogFactory.getLog(DisplayItemsSynchronousThreadsImpl.class);
  
  /**
   * The all items fetched from database boolean check.
   */
  private Boolean allItemsFetchedFromDatabase = false;
  
  /**
   * The item entities list.
   */
  private final List<ItemEntity> itemEntities = new ArrayList<>();
  
  /**
   * The number of items fetched.
   */
  private int numberOfItems;
  
  @Override
  public void fetchItemDataFromResultSet(ResultSet resultSet)
      throws DatabaseException, AttributeParseException, InterruptedException {
    final ItemParser itemParser = new ItemParserImpl();
    try {
      while (resultSet.next()) {
        synchronized (this) {
          final String itemName =
              itemParser.parseName(resultSet.getString("name"));
          final double itemPrice = itemParser.parsePrice(resultSet.getString(
              "price"));
          final double itemQuantity = itemParser.parseQuantity(resultSet.getString(
              "quantity"));
          final ItemType itemType = itemParser.parseType(resultSet.getString("type"));
          final ItemEntity item = new ItemEntity(itemName, itemPrice,
              itemQuantity, itemType);
          itemEntities.add(item);
          notifyAll();
          Thread.sleep(500);
        }
      }
    } catch (SQLException exception) {
      logger.error(String.format(DatabaseConstantsUtils.EXCEPTION_WHILE_FETCHING_DATA,
          exception.getMessage()));
      throw new DatabaseException(String.format(
          DatabaseConstantsUtils.EXCEPTION_WHILE_FETCHING_DATA, exception.getMessage()), exception);
    }
    allItemsFetchedFromDatabase = true;
    logger.info(String.format(DatabaseConstantsUtils.ITEMS_FETCHED,
        numberOfItems));
    System.out.println(String.format(DatabaseConstantsUtils.ITEMS_FETCHED,
        numberOfItems));
  }
  
  @Override
  public void calculateTaxForTheItems() throws InterruptedException {
    while (!allItemsFetchedFromDatabase) {
      synchronized (this) {
        if (itemEntities.isEmpty()) {
          wait();
        } else {
          final ItemEntity itemEntity = itemEntities.remove(0);
          numberOfItems += 1;
          setTaxForTheNewItem(itemEntity);
          printItemDetails(itemEntity);
          notifyAll();
          Thread.sleep(500);
        }
      }
    }
  }
  
  private void printItemDetails(ItemEntity item) {
    System.out.printf(StringConstantsUtils.SHOW_ITEM_LIST_INFO,
        numberOfItems, item.getType(), item.getName(), item.getPrice(),
        item.getQuantity(), item.getSalesTax(), item.getFinalPrice());
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
