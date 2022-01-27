package com.nuclei.assignment.service.displayitem;

import com.nuclei.assignment.constants.DatabaseConstantsUtils;
import com.nuclei.assignment.constants.FlagsConstantsUtils;
import com.nuclei.assignment.constants.StringConstantsUtils;
import com.nuclei.assignment.entity.ItemEntity;
import com.nuclei.assignment.enums.ItemType;
import com.nuclei.assignment.exception.AttributeParseException;
import com.nuclei.assignment.service.itemparser.ItemParser;
import com.nuclei.assignment.service.itemparser.ItemParserImpl;
import com.nuclei.assignment.service.tax.ItemTax;
import com.nuclei.assignment.service.tax.ItemTaxImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
   * The item entities list.
   */
  private final List<ItemEntity> itemEntities = new ArrayList<>();
  
  /**
   * The number of items fetched.
   */
  private int numberOfItems;
  
  @Override
  public void displayItems(List<Map<String, String>> items) throws InterruptedException {
    final List<Thread> threadPool = new ArrayList<>();
    final int threadSize = Math.min(5, items.size());
    final int itemsLength = items.size();
    int start = 0;
    int end = (int) Math.ceil((double) itemsLength / threadSize);
    final int difference = end - start;
  
    for (int index = 0; index < threadSize; index++) {
      threadPool.add(fetchItemDataFromRawDataThread(items, start, end));
      start = end;
      end += difference;
      end = Math.min(end, itemsLength);
    }
  
    for (int index = 0; index < threadSize; index++) {
      threadPool.add(calculateTaxForTheItemsThread(itemsLength));
    }
    for (final Thread thread : threadPool) {
      thread.start();
    }
    for (final Thread thread : threadPool) {
      thread.join();
    }
  }

  private Thread fetchItemDataFromRawDataThread(final List<Map<String, String>> items,
                                        final int start, final int end) {
    return new Thread(() -> {
      try {
        for (final Map<String, String> rawDataOfItem : items.subList(start, end)) {
          synchronized (this) {
            final ItemEntity item = createItemFromRawData(rawDataOfItem);
            itemEntities.add(item);
            notifyAll();
            //Thread.sleep(200);
          }
        }
      } catch (Exception exception) {
        logger.error(String.format(DatabaseConstantsUtils.EXCEPTION_WHILE_FETCHING_DATA,
            exception.getMessage()));
      }
    });
  }
  
  private Thread calculateTaxForTheItemsThread(final int itemsLength) {
    return new Thread(() -> {
      try {
        while (true) {
          synchronized (this) {
            if (numberOfItems == itemsLength) {
              break;
            }
            if (itemEntities.isEmpty()) {
              wait();
            } else {
              final ItemEntity itemEntity = itemEntities.remove(0);
              numberOfItems += 1;
              setTaxForTheNewItem(itemEntity);
              printItemDetails(itemEntity);
              notifyAll();
              //Thread.sleep(200);
            }
          }
        }
      } catch (InterruptedException exception) {
        logger.error(String.format(DatabaseConstantsUtils.EXCEPTION_WHILE_FETCHING_DATA,
            exception.getMessage()));
      }
    });
  }
  
  private ItemEntity createItemFromRawData(Map<String, String> rawDataOfItem)
      throws AttributeParseException {
    final ItemParser itemParser = new ItemParserImpl();
    final String itemName =
        itemParser.parseName(rawDataOfItem.get(FlagsConstantsUtils.NAME_FLAG));
    final double itemPrice = itemParser.parsePrice(rawDataOfItem.get(
        FlagsConstantsUtils.PRICE_FLAG));
    final double itemQuantity = itemParser.parseQuantity(rawDataOfItem.get(
        FlagsConstantsUtils.QUANTITY_FLAG));
    final ItemType itemType =
        itemParser.parseType(rawDataOfItem.get(FlagsConstantsUtils.TYPE_FLAG));
    return new ItemEntity(itemName, itemPrice, itemQuantity, itemType);
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
