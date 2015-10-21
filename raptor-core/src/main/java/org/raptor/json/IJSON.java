package org.raptor.json;

/**
 * Created by Anant on 18-08-2015.
 */
public interface IJSON {
    public <T> T getInstance(String jsonString, Class<T> type);
    public <T> String getJsonString(T object);
}
