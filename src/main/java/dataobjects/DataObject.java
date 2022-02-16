package dataobjects;

import com.eclipsesource.json.*;

public interface DataObject {
    int getId();
    String getName();
    JsonObject serialize();
}
