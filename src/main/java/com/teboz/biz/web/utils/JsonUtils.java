package com.teboz.biz.web.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;

/**
 * 
 * 对incadea接口中获得的json数据进行解析<br>
 * 
 * @author tao.huang
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public final class JsonUtils {
    
    public static final String ARR_NAME = "value"; // json中存值的key

    public static final String ERROR = "odata.error"; // json中存错误的key
    
    public static final Integer FRIST = 0; // 获取数组中的第一个

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonUtils.class);

    private JsonUtils() {
    }

    /**
     * 
     * 将dms中的json转换成需要list 如果json中含有错误记录日志并返回空，如果json中没有数据返回一个空的对象
     * 
     * @param json
     *            dms中的json
     * @param params
     *            需要的字段
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static List<Map<String, Object>> getJosnList(final JSONObject json, final String... params) {
        List<Map<String, Object>> result = null;
        if (json == null) {
            return result;
        }
        if (null != json.get(ERROR)) {
            LOGGER.error("调用接口异常" + json.toJSONString());
            return result;
        }
        result = new ArrayList<Map<String, Object>>();
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> list = (List<Map<String, Object>>) json.get(ARR_NAME);
        if (null == list || list.isEmpty() || null == params) {
            return result;
        }
        Map<String, Object> resultEntity = null;
        for (Map<String, Object> entity : list) {
            resultEntity = new HashMap<String, Object>();
            for (String param : params) {
                resultEntity.put(standardField(param), null == entity.get(param) ? "" : entity.get(param));
            }
            result.add(resultEntity);
        }
        return result;
    }

    public static Map<String, Object> getJosn(final JSONObject json, final String... params) {
        Map<String, Object> resultEntity = null;
        if (json == null) {
            return resultEntity;
        }
        if (null != json.get(ERROR)) {
            LOGGER.error("调用接口异常" + json.toJSONString());
            return resultEntity;
        }
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> list = (List<Map<String, Object>>) json.get(ARR_NAME);
        resultEntity = new HashMap<String, Object>();
        if (null == list || list.isEmpty() || null == params) {
            return resultEntity;
        }
        Map<String, Object> entity = list.get(FRIST);
        for (String param : params) {
            resultEntity.put(standardField(param), null == entity.get(param) ? "" : entity.get(param));
        }
        return resultEntity;
    }

    
    public static Map<String, Object> getJsonMap(JSONObject json, String... params) {
        Map<String, Object> resultEntity = new HashMap<String, Object>();
        if(null == json) {
            return null;
        }
        if(null != json.get(ERROR)) {
            LOGGER.error("调用接口异常" + json.toJSONString());
            return null;
        }
        try{
            Map<String, Object> map = (Map<String, Object>) json;
            for(String param : params) {
                resultEntity.put(standardField(param), null == map.get(param) ? "" : map.get(param));
            }
        } catch (Exception e) {
            LOGGER.error("调用接口异常" + json.toJSONString() + e);
            return null;
        }
        return resultEntity;
    }
    
    public static String standardField(String fieldName) {
        if (StringUtils.isBlank(fieldName)) {
            return fieldName;
        }
        return ((fieldName.substring(0, 1)).toLowerCase() + fieldName.substring(1)).replaceAll("_", "").replaceAll(" ",
                "");
    }
}
