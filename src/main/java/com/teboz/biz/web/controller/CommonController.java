package com.teboz.biz.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.teboz.biz.web.utils.JsonResult;
import com.teboz.biz.web.utils.JsonpUtils;

@Controller
public class CommonController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommonController.class);

    @RequestMapping(value = "/test")
    @ResponseBody
    public Object test(String id, String jsoncallback) {
        JsonResult result = new JsonResult(true, false);
        try {
            return JsonpUtils.resultFormatJsonp(jsoncallback, result);
        } catch (Exception e) {
            LOGGER.error("error", e);
        }
        return JsonpUtils.resultFormatJsonp(jsoncallback, false);
    }
}
