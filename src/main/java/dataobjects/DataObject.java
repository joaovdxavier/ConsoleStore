package dataobjects;

import com.eclipsesource.json.*;

public interface DataObject {
    //Pontos: 1
    //Renan
    int getId();
    String getName();
    JsonObject serialize();
}
