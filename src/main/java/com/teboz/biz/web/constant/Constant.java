/*
 * Copyright (C), 2014-2015, 上海澍勋电子商务有限公司
 * FileName: Constant.java
 * Author:   long.yu
 * Date:     2015年12月21日 下午5:58:07
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.teboz.biz.web.constant;

import java.text.SimpleDateFormat;

/**
 * 静态变量
 *
 * @author long.yu
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class Constant {

    /**
     * 后台登录用户的session的key
     */
    public static final String SESSION_SYS_USER = "SYS_USER_SESSION";

    
    public static final String APP_ENTRY_NO = "APP_Entry_No";//POST一个请求过程的实例号
    public static final String REQUEST_ACTION = "Request_Action";//POST请求动作标识
    public static final String CODE = "code";//POST请求返回代码
    public static final String MSG = "msg";//POST请求返回消息名称
    public static final String REQUEST_SUCCESS = "请求成功！";//POST外层返回消息
    
    public static final String INTERFACE_PARAM_MSG= "门店接口参数不能为空！";
    public static final String PARAM_NULL_MSG ="参数不能为空";
    
    
    public static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
}
