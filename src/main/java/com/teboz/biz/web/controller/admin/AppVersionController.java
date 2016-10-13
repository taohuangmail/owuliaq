/*
 * Copyright (C), 2014-2015, 上海澍勋电子商务有限公司
 * FileName: AppManagerController.java
 * Author:   long.yu
 * Date:     2015年12月22日 下午1:26:20
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.teboz.biz.web.controller.admin;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.teboz.biz.model.AppVersion;
import com.teboz.biz.page.AjaxDataTableVO;
import com.teboz.biz.page.PaginationResult;
import com.teboz.biz.page.RetMsg;
import com.teboz.biz.service.AppVersionService;
import com.teboz.biz.web.admin.bean.AppVersionQueryBean;

/**
 * app后台管理
 *
 * @author long.yu
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Controller
@RequestMapping("admin/auth")
public class AppVersionController extends AdminBaseController {

    @Autowired
    private AppVersionService appVersionService;

    @RequestMapping(value = "/appVersion/appVersionList")
    public String appVersionList() {
        return "app/appVersionList";
    }

    @RequestMapping("/appVersion/infoAjaxList.json")
    @ResponseBody
    public AjaxDataTableVO<AppVersion> userPayAjaxList(AppVersionQueryBean queryBean, HttpServletRequest request) {
        List<AppVersion> list = appVersionService.getListByPage(queryBean);
        PaginationResult<List<AppVersion>> result = new PaginationResult<List<AppVersion>>(list,
                queryBean.getPagination());
        return new AjaxDataTableVO<AppVersion>(queryBean.getsEcho(), result);
    }

    @RequestMapping(value = "/appVersion/addAppVersion")
    public String addAppVersion() {
        return "app/editAppVersion";
    }

    @RequestMapping(value = "/appVersion/editAppVersion")
    public String editAppVersion(Integer id, Model model) {
        AppVersion appVersion = appVersionService.selectByPrimaryKey(id);
        model.addAttribute("appVersion", appVersion);
        return "app/editAppVersion";
    }

    @RequestMapping(value = "/appVersion/saveAppVersion")
    @ResponseBody
    public RetMsg saveAppVersion(AppVersion appVersion) {
        Date now = new Date();
        Integer id = appVersion.getId();
        appVersion.setUpdateTime(now);
        appVersion.setUpdateUserId(1);
        if (id == null) {
            appVersion.setDeleteFlag(false);
            appVersion.setCreateTime(now);
            appVersion.setCreateUserId(1);
            appVersionService.insertSelective(appVersion);
        } else {
            appVersionService.updateByPrimaryKeySelective(appVersion);
        }
        return RetMsg.createRetMsg("true", "保存成功");
    }

    @RequestMapping(value = "/appVersion/delAppVersion")
    @ResponseBody
    public RetMsg delAppVersion(Integer id) {
        AppVersion record = new AppVersion();
        record.setId(id);
        record.setDeleteFlag(true);
        appVersionService.updateByPrimaryKeySelective(record);
        return RetMsg.createRetMsg("true", "删除成功");
    }
}
