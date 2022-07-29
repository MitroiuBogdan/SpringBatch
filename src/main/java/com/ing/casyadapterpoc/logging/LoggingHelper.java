package com.ing.casyadapterpoc.logging;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class LoggingHelper {

    private final static ObjectMapper OM = new ObjectMapper();

    public static String buildLogMessage(Object object){
        String message = "";

        try {
            message = "Displaying: "+ OM.writerWithDefaultPrettyPrinter().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return message;
    }
}
