package com.teboz.biz.web.utils;

import java.util.Iterator;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JSONtoLowerTools {

    public static JSONObject transObject(JSONObject jsonObject) {
        JSONObject result = new JSONObject();
        @SuppressWarnings("unchecked")
        Iterator<String> it = jsonObject.keys();
        while (it.hasNext()) {
            String key = (String) it.next();
            Object object = jsonObject.get(key);
            if (object.getClass().toString().endsWith("String")) {
                result.accumulate(JsonUtils.standardField(key), object);
            } else if (object.getClass().toString().endsWith("JSONObject")) {
                result.accumulate(JsonUtils.standardField(key), JSONtoLowerTools.transObject((JSONObject) object));
            } else if (object.getClass().toString().endsWith("JSONArray")) {
                result.accumulate(JsonUtils.standardField(key),
                        JSONtoLowerTools.transArray(jsonObject.getJSONArray(key)));
            }
        }
        return result;
    }
    
    public static JSONArray transArray(JSONArray jsonObject){
        JSONArray result = new JSONArray();
        for (int i = 0; i < jsonObject.size(); i++) {
            Object jArray=jsonObject.getJSONObject(i);
            if(jArray.getClass().toString().endsWith("JSONObject")){
                result.add(JSONtoLowerTools.transObject((JSONObject)jArray));
            }else if(jArray.getClass().toString().endsWith("JSONArray")){
                result.add(JSONtoLowerTools.transArray((JSONArray)jArray));
            }
        }
        return result;
    }

}