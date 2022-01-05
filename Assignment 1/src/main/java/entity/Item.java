package entity;

import constants.ExceptionsConstants;
import enums.ItemType;
import exception.CustomException;
import lombok.Getter;
import lombok.Setter;
import service.tax.ItemTax;
import service.tax.ItemTaxImpl;


/**
 * Item Entity class
 * **/
@Getter
@Setter
public class Item {
    private String name;

    private Double price;
    private Double salesTax;
    private Double finalPrice;

    private Double quantity;
    private ItemType type;

    public Item(String name, Double price, Double quantity, ItemType type) throws CustomException {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.type = type;

        ItemTax itemTaxService = new ItemTaxImpl();
        switch (type) {
            case RAW:
                this.salesTax = itemTaxService.rawItemTaxCalculation(price);
                break;
            case MANUFACTURED:
                this.salesTax = itemTaxService.manufacturedItemTaxCalculation(price);
                break;
            case IMPORTED:
                this.salesTax = itemTaxService.importedItemTaxCalculation(price);
                break;
            default:
                throw new CustomException(ExceptionsConstants.INVALID_TYPE);
        }
        this.finalPrice = this.salesTax + price;
    }

}
