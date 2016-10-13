/*
 * Copyright (C), 2014-2015, 上海澍勋电子商务有限公司
 * FileName: AdminBaseController.java
 * Author:   long.yu
 * Date:     2015年12月22日 上午9:54:05
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.teboz.biz.web.controller.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.teboz.biz.model.SysUser;
import com.teboz.biz.web.constant.Constant;

/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 * 
 * @author long.yu
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public abstract class AdminBaseController {

    /**
     * 日志
     */
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @ModelAttribute
    public void setAttr(HttpServletRequest request, HttpServletResponse response, Model model) {
        model.addAttribute("base", request.getContextPath());
    }

    @ExceptionHandler
    public String exp(HttpServletRequest request, Exception ex) {

        request.setAttribute("ex", ex);

        logger.error(ExceptionUtils.getFullStackTrace(ex));
        throw new RuntimeException(ex);
    }
    
    public Integer getUserId(HttpServletRequest request){
    	return ((SysUser)request.getSession().getAttribute(Constant.SESSION_SYS_USER)).getId();
    }
}
