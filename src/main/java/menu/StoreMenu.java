package menu;

import datamanagment.CurrentDataSingleton;
import exceptions.NonExistentProductId;
import exceptions.NotLoggedInException;
import inpututils.InputUtil;
import dataobjects.Product;
import java.io.IOException;

public class StoreMenu implements MenuItem {
    @Override
    public void displayMenu() throws NotLoggedInException, NonExistentProductId, IOException {
        System.out.println("Product list.");
        for (Product product : CurrentDataSingleton.getInstance().getProducts()) {
            System.out.print("Product id: " + product.getId());
            System.out.println(". Product name: " + product.getName());
        }

        System.out.println("Type id of product to watch and add it to basket.\n" +
                "Type 0 to return to Main menu.\n");

        int variant = InputUtil.getInt();

        if (variant < 0 || variant > (CurrentDataSingleton.getInstance().getProducts().size())) {
            throw new NonExistentProductId();
        } else {
            if (variant != 0) {
                CurrentDataSingleton.getInstance().setCurrentProductId(variant);
                MenuManager.getInstance().displaySelectedMenu(5);
            }
        }
    }

    @Override
    public int getMenuID() {
        return 1;
    }
}
