package main.java.menu;

import main.java.datamanagment.DataBasketManager;
import main.java.datamanagment.DataStoreManager;
import main.java.enums.MenuNames;
import main.java.exceptions.NonExistentProductId;
import main.java.exceptions.NotLoggedInException;
import main.java.inpututils.InputUtil;

import javax.xml.transform.Source;
import java.io.IOException;

public class BasketMenu implements MenuItem {
    private /*@ spec_public non_null @*/ static String productShortInfo = "Product name: %s; Count: %s.";
    private /*@ spec_public non_null @*/ static String countInfo = "Total cost of items in the basket: %s";

    /*@ spec_public nullable @*/ static double countPrice;

    @Override
    /*@ also 
    @   assignable \everything; 
    @   signals_only IOException;
    @ also
    @   assignable \everything;
    @   ensures BasketMenu.countPrice != \old(BasketMenu.countPrice);
    @   signals_only IOException;
    @*/
    public void displayMenu() throws IOException {
        countPrice = 0;

        System.out.println("Your basket: ");

        if (DataBasketManager.getInstance().getUserBasket().isEmpty()) {
            System.out.println("Your basket is empty.");
        } else {
            DataBasketManager.getInstance().getUserBasket().forEach((id, count) -> {
                try {
                    DataStoreManager.getInstance().getProducts().forEach(product -> {
                        if (product.getId() == id) {
                            System.out.println(String.format(productShortInfo, product.getName(), count));
                            countPrice += product.getPrice() * count;
                        }
                    });
                } catch (IOException | NotLoggedInException | NonExistentProductId exception) {
                    exception.printStackTrace();
                }
            });
            System.out.println(String.format(countInfo, countPrice));
        }

        int paragraph;
        do {
            System.out.println("1. Type 1 to clear basket.\n" +
                    "2. Type 2 to return to Main menu\n");
            paragraph = InputUtil.getIntFromConsole();
            if (paragraph == 1) {
                if (DataBasketManager.getInstance().getUserBasket().isEmpty()) {
                    System.out.println("Your basket is already empty.");
                } else {
                    DataBasketManager.getInstance().getUserBasket().clear();
                    System.out.println("Basket was successfully cleared! ");
                }
                return;
            } else if (paragraph == 2) {
                return;
            }
        } while (paragraph != 1 & paragraph != 2);
    }

    @Override
    /*@ also
    @ assignable \nothing;
    @*/
    public /*@ pure @*/ MenuNames getMenuName() {
        return MenuNames.BASKET;
    }
}
