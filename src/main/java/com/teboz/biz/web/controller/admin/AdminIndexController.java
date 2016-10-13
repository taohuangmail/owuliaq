/*
 * Copyright (C), 2014-2015, 上海澍勋电子商务有限公司
 * FileName: AdminIndexController.java
 * Author:   long.yu
 * Date:     2015年12月22日 上午10:31:38
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.teboz.biz.web.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 后台首页
 *
 * @author long.yu
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Controller
@RequestMapping("admin/auth")
public class AdminIndexController extends AdminBaseController {

    @RequestMapping(value = "/index")
    public String index() {
        return "index";
    }
}
