package menu;

import datamanagment.CurrentDataSingleton;
import exceptions.NonExistentProductId;
import exceptions.NotLoggedInException;
import inpututils.InputUtil;

import java.io.IOException;

public class BasketMenu implements MenuItem {
    static double countPrice;

    @Override
    public void displayMenu() throws IOException {
        countPrice = 0;

        System.out.println("Your basket: ");

        if (CurrentDataSingleton.getInstance().getUserBasket().isEmpty()) {
            System.out.println("Your basket is empty.");
        } else {
            CurrentDataSingleton.getInstance().getUserBasket().forEach((id, count) -> {
                try {
                    CurrentDataSingleton.getInstance().getProducts().forEach(product -> {
                        if (product.getId() == id) {
                            System.out.println("Product name: " + product.getName() + " Count: " + count);
                            countPrice += product.getPrice() * count;
                        }
                    });
                } catch (IOException | NotLoggedInException | NonExistentProductId exception) {
                    exception.printStackTrace();
                }
            });
            System.out.println("Total cost of items in the basket: " + countPrice);
        }

        int paragraph;
        do {
            System.out.println("1. Type 1 to clear basket.\n" +
                    "2. Type 2 to return to Main menu\n");
            paragraph = InputUtil.getInt();
            if (paragraph == 1) {
                CurrentDataSingleton.getInstance().getUserBasket().clear();
                System.out.println("Basket was successfully cleared! ");
                return;
            } else if (paragraph == 2) {
                return;
            }
        } while (paragraph != 1 & paragraph != 2);
    }

    @Override
    public int getMenuID() {
        return 2;
    }
}
