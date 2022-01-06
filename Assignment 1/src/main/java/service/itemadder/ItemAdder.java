package service.itemadder;

import entity.Item;
import java.util.List;

/**
 * Item Adder interface.
 * **/
public interface ItemAdder {

  void inputItemsFromUserInterface(String[] rawData);

  void outputItemsWithTaxToUser(List<Item> items);
}
