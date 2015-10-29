package org.raptor.json;

import com.google.gson.Gson;

/**
 * Created by Anant on 18-08-2015.
 */
public class GsonJSONImpl implements IJSON {
    Gson gson;

    public GsonJSONImpl() {
        gson = new Gson();
    }

    public <T> T getInstance(String jsonString, Class<T> type) {
        return gson.fromJson(jsonString, type);
    }

    public <T> String getJsonString(T object) {
        return gson.toJson(object);
    }
}
