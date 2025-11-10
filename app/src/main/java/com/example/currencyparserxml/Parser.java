package com.example.currencyparserxml;

import org.json.JSONObject;
import java.util.HashMap;
import java.util.Iterator;

public class Parser {
    public static HashMap<String, Double> parseRates(String jsonData) {
        HashMap<String, Double> map = new HashMap<>();
        try {
            JSONObject obj = new JSONObject(jsonData);
            JSONObject rates = obj.getJSONObject("rates");
            Iterator<String> keys = rates.keys();
            while (keys.hasNext()) {
                String code = keys.next();
                map.put(code, rates.getDouble(code));
            }
        } catch (Exception ignored) {}
        return map;
    }
}
