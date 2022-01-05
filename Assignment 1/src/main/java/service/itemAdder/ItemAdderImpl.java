package service.itemAdder;

import constants.ExceptionsConstants;
import entity.Item;
import enums.ItemType;
import exception.CustomException;

import java.util.Scanner;

/**
 * Item Adder Implementation containing method to add items.
 * **/
public class ItemAdderImpl implements ItemAdder {

    @Override
    public void itemAdder() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Item Details (as comma separated input in terminal):");
        System.out.println("name,price,quantity,type");
        System.out.println("Note `type` to be inputted as");
        int itemTypeCount = 1;
        for (ItemType type : ItemType.values()) {
            System.out.printf("%d. %s\n", itemTypeCount, type);
            itemTypeCount += 1;
        }

        String addMoreToItems;
        do {
            System.out.println("Insert Item Details:");
            try{
                String input = scanner.next();
                Item item = createItem(input);
                System.out.printf("%s Item %s with price %.2f and quantity %.2f at tax %.2f => final price as %.2f\n", item.getType(), item.getName(), item.getPrice(), item.getQuantity(), item.getSalesTax(), item.getFinalPrice());
            } catch (Exception exception){
                System.out.println(exception.getMessage());
            } finally {
                System.out.println("Do you want to enter details of any other item (y/n):");
                addMoreToItems = scanner.next();
            }
        } while (addMoreToItems.equals("y"));

        scanner.close();
    }

    public Item createItem(String item) throws CustomException {
        String[] itemProperties = item.split(",");

        // making sure that length of itemProperties is 4
        if (itemProperties.length != 4) {
            throw new CustomException(ExceptionsConstants.INVALID_INPUT);
        }

        String itemName = itemProperties[0];
        // itemName isNotEmpty
        if (itemName.isEmpty()) {
            throw new CustomException(ExceptionsConstants.INVALID_NAME);
        }

        double itemPrice;
        // itemPrice is double parsable and positive
        try {
            itemPrice = Double.parseDouble(itemProperties[1]);
            if (itemPrice < 0) {
                throw new CustomException(ExceptionsConstants.INVALID_PRICE);
            }
        } catch (Exception exception){
            throw new CustomException(ExceptionsConstants.INVALID_PRICE);
        }

        double itemQuantity;
        // itemQuantity is double parsable and positive
        try {
            itemQuantity = Double.parseDouble(itemProperties[2]);
            if (itemQuantity < 0) {
                throw new CustomException(ExceptionsConstants.INVALID_QUANTITY);
            }
        } catch (Exception exception){
            throw new CustomException(ExceptionsConstants.INVALID_QUANTITY);
        }

        ItemType itemType;
        try {
            itemType = ItemType.valueOf(itemProperties[3]);
        } catch (Exception exception){
            throw new CustomException(ExceptionsConstants.INVALID_TYPE);
        }
        return new Item(itemName, itemPrice, itemQuantity, itemType);
    }
}
