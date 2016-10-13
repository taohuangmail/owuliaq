package com.teboz.biz.web.utils;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSONObject;

public final class JsonpUtils {

    public static Object resultFormatJsonp(String callback, Object data) {
        if (StringUtils.isNotEmpty(callback)) {
            String object = JSONObject.toJSONString(data);
            return callback + "(" + object + ");";
        } else {
            return JSONObject.parse(JSONObject.toJSONString(data));
        }
    }
    
    public static Object resultFormatJsonp(String callback, Object data, Integer versionNo) {
        if (StringUtils.isNotEmpty(callback)) {
            String object = JSONObject.toJSONString(data);
            return callback + "(" + object + ");";
        } else {
            if (versionNo >= 130) {
                return JSONObject.toJSONString(data);
            } else {
                return data;
            }
        }
    }
}
