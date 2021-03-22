package datamanagment;

import com.fasterxml.jackson.databind.ObjectMapper;
import productpattern.Product;
import userpattern.User;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DataWriteUtil {

    public static void writeProduct(Product product) throws IOException {
        String productFileName = ".\\src\\main\\resources\\products\\id" + product.getProductID() + ".json";
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File(productFileName), product);
    }

    public static void writeUsers(User user) throws IOException {
        String userFileName = ".\\src\\main\\resources\\users\\user" + user.getUserID() + ".json";
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File(userFileName), user);
    }
}
