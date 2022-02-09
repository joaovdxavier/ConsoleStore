package menu;

import datamanagment.DataBasketManager;
import datamanagment.DataStoreManager;
import enums.MenuNames;
import exceptions.NonExistentProductId;
import exceptions.NotLoggedInException;
import inpututils.InputUtil;

import javax.xml.transform.Source;
import java.io.IOException;

public class BasketMenu implements MenuItem {
    //Pontos: 3
    //Lucas
    private static String productShortInfo = "Product name: %s; Count: %s.";
    private static String countInfo = "Total cost of items in the basket: %s";

    static double countPrice;

    @Override
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
    public MenuNames getMenuName() {
        return MenuNames.BASKET;
    }
}
