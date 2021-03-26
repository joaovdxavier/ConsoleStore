package menu;

import inpututils.InputUtil;

public class BasketMenu {
    static double countPrice;

    public static void displayBasketMenu(MenuData menuData) {
        countPrice = 0;

        System.out.println("Your basket: ");

        if (menuData.getUserBasket().isEmpty()) {
            System.out.println("Your basket is empty.");
        } else {
            menuData.getUserBasket().forEach((id, count) -> {
                menuData.getProducts().forEach(product -> {
                    if (product.getProductID() == id) {
                        System.out.println("Product name: " + product.getName() + " Count: " + count);
                        countPrice += product.getPrice() * count;
                    }
                });
            });
            System.out.println("Total cost of items in the basket: " + countPrice);
        }


        int paragraph;
        do {
            System.out.println("1. Type 1 to clear basket.\n" +
                    "2. Type 2 to return to main menu\n");
            paragraph = InputUtil.getInt();
            if (paragraph == 1) {
                menuData.getUserBasket().clear();
                System.out.println("Basket was successfully cleared! ");
                return;
            } else if (paragraph == 2) {
                return;
            }
        } while (paragraph != 1 & paragraph != 2);
    }
}
