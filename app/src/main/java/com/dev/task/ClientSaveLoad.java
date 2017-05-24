package com.dev.task;

import java.io.IOException;
import java.io.StringReader;
import java.util.*;

public class ClientSaveLoad {

    public static Map<String, String> strToMap(String str){
        Properties props = new Properties();
        try {
            props.load(new StringReader(str.substring(1, str.length() - 1).replace(", ", "\n")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Map<String, String> map = new HashMap<String, String>();
        for (Map.Entry<Object, Object> e : props.entrySet()) {
            map.put((String)e.getKey(), (String)e.getValue());
        }
        return map;
    }

}
