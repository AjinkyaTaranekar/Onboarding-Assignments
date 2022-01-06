import service.itemadder.ItemAdderImpl;

/**
 * Assignment 1.
 * */
public class Application {
  public static void main(final String[] args) {
    new ItemAdderImpl().inputItemsFromUserInterface(args);
  }
}
