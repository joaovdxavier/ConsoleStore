package menu;

import datamanagment.CurrentDataSingleton;
import dataobjects.Product;
import exceptions.NonExistentProductId;
import exceptions.NotLoggedInException;
import inpututils.InputUtil;
import java.io.IOException;

public class ProductAddingMenu implements MenuItem {
    @Override
    public void displayMenu() throws IOException, NotLoggedInException, NonExistentProductId {
        Product productToAdd = CurrentDataSingleton.getInstance().getCurrentProduct();

        System.out.println("How many of " +
                productToAdd.getName() +
                " do you wanna add to basket? (from 1 to 100000)");
        int count;
        do {
            count = InputUtil.getInt();
        } while (count < 1 || count > 100000);

        if (CurrentDataSingleton.getInstance().getUserBasket().containsKey(productToAdd.getId())) {
            int previousCount = CurrentDataSingleton.getInstance().getUserBasket().get(productToAdd.getId());
            int newCount = previousCount + count;
            CurrentDataSingleton.getInstance().getUserBasket().put(productToAdd.getId(), newCount);
        } else {
            CurrentDataSingleton.getInstance().getUserBasket().put(productToAdd.getId(), count);
        }

        System.out.println("Product was added successfully!");
        return;
    }

    @Override
    public int getMenuID() {
        return 7;
    }
}
