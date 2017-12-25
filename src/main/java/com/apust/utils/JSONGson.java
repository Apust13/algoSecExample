package com.apust.utils;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.*;
import java.lang.reflect.Type;


public class JSONGson {
    public static String getJsonFromFile(String pathToFile)  {
        String line = "";
        String json = "";
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(pathToFile)));
            StringBuilder sb = new StringBuilder();

        while (line != null) {
            line = bufferedReader.readLine();
            sb.append(line);
        }

        json = sb.toString();

        } catch (IOException e){
            e.printStackTrace();
        }
        finally {
            return json;
        }
    }

    public static Object readJsonToClass(String json, Type type){
        Gson gson = new Gson();
        JsonReader reader = new JsonReader(new StringReader(json));
        reader.setLenient(true);
        Object object = gson.fromJson(reader, type);
        return object;
    }
}
