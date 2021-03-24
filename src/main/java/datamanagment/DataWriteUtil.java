package datamanagment;

import com.fasterxml.jackson.databind.ObjectMapper;
import productpattern.Product;
import userpattern.User;
import java.io.File;
import java.io.IOException;

public class DataWriteUtil {

    public static void writeProduct(Product product) throws IOException {
        String productFileName = ".\\src\\main\\resources\\products\\id" + product.getProductID() + ".json";
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File(productFileName), product);
    }

    public static void writeUser(User user) throws IOException {
        String userFileName = ".\\src\\main\\resources\\users\\user" + user.getUserID() + ".json";
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File(userFileName), user);
    }
}
