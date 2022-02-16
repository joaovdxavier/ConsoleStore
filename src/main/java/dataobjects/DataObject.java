package main.java.dataobjects;

import main.java.com.eclipsesource.json.*;

public interface DataObject {
    int getId();
    String getName();
    JsonObject serialize();
}
