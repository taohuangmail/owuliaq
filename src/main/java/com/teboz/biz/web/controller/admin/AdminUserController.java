/*
 * Copyright (C), 2014-2015, 上海澍勋电子商务有限公司
 * FileName: UserController.java
 * Author:   long.yu
 * Date:     2015年12月16日 下午1:55:25
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.teboz.biz.web.controller.admin;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.teboz.biz.model.SysUser;
import com.teboz.biz.model.SysUserExample;
import com.teboz.biz.model.base.BaseSysUserExample;
import com.teboz.biz.page.AjaxDataTableVO;
import com.teboz.biz.page.PaginationResult;
import com.teboz.biz.page.RetMsg;
import com.teboz.biz.service.SysUserService;
import com.teboz.biz.web.admin.bean.SysUserQueryBean;
import com.teboz.biz.web.constant.Constant;

/**
 * 用户控制器
 * 
 * @author tahu
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Controller
@RequestMapping("admin")
public class AdminUserController extends AdminBaseController {

    @Autowired
    private SysUserService sysUserService;

    @RequestMapping(value = "/toLogin")
    public String toLogin() {
        return "login";
    }

    @RequestMapping(value = "/login")
    public String login(String username, String password, HttpServletRequest request, HttpServletResponse response,
            Model model) throws IOException {
        if (StringUtils.isEmpty(username)) {
            model.addAttribute("msg", "用户名不能为空");
            return "login";
        }
        SysUserExample example = new SysUserExample();
        BaseSysUserExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(username);
        List<SysUser> sysUserList = sysUserService.selectByExample(example);
        if (sysUserList == null || sysUserList.size() < 1) {
            model.addAttribute("msg", "账户不存在");
            return "login";
        }
        SysUser sysUser = sysUserList.get(0);
        String md5Password = DigestUtils.md5Hex(password);
        HttpSession session = request.getSession();
        if (!md5Password.equals(sysUser.getPassword())) {
            model.addAttribute("msg", "用户或密码不正确");
            return "login";
        }
        session.setAttribute(Constant.SESSION_SYS_USER, sysUser);
        response.sendRedirect("auth/index.htm");
        return null;
    }

    @RequestMapping(value = "/auth/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        session.removeAttribute(Constant.SESSION_SYS_USER);
        session.invalidate();
        return "login";
    }

    @RequestMapping(value = "/auth/editPwd")
    public String editPwd() {
        return "editPwd";
    }

    @RequestMapping(value = "/auth/savePwd")
    @ResponseBody
    public RetMsg savePwd(String password, String newPassword, HttpServletRequest request) {
        SysUser sysUser = (SysUser) request.getSession().getAttribute(Constant.SESSION_SYS_USER);
        if (password.equals(newPassword)) {
            return RetMsg.createRetMsg("false", "新密码不能与原密码一致");
        }
        if (!DigestUtils.md5Hex(password).equals(sysUser.getPassword())) {
            return RetMsg.createRetMsg("false", "原密码不正确");
        }
        sysUser.setPassword(DigestUtils.md5Hex(newPassword));
        sysUserService.updateByPrimaryKeySelective(sysUser);
        request.getSession().setAttribute(Constant.SESSION_SYS_USER, sysUser);
        return RetMsg.createRetMsg("true", "修改密码成功");
    }
    
    @RequestMapping(value = "/auth/user/index")
    public String index(Model model ,HttpServletRequest request){
    	return "/user/userList";
    }
    
    @RequestMapping("/auth/user/infoAjaxList.json")
    @ResponseBody
    public AjaxDataTableVO<SysUser> userPayAjaxList(SysUserQueryBean queryBean, HttpServletRequest request) {
		queryBean.getPagination().setTotalRows(sysUserService.getSysUserCount(queryBean));
		List<SysUser> list= sysUserService.getSysUserList(queryBean);
        PaginationResult<List<SysUser>> result = new PaginationResult<List<SysUser>>(list, queryBean.getPagination());
        return new AjaxDataTableVO<SysUser>(queryBean.getsEcho(), result);
    }
    
    
    @RequestMapping("/auth/user/addUser.json")
    public String addUser(Model model ,HttpServletRequest request){
    	model.addAttribute("isEdit", false);
    	return "/user/editUser";
    }
    
    @RequestMapping("/auth/user/editUser.json")
    public String editUser(Model model ,HttpServletRequest request ,Integer id){
    	if(null != id){
        	model.addAttribute("sysUser",sysUserService.selectByPrimaryKey(id));
    	}
    	model.addAttribute("isEdit", true);
    	return "/user/editUser";
    }
    
    @RequestMapping("/auth/user/saveUser.json")
    @ResponseBody
    public RetMsg saveUser(Model model ,SysUser sysUser,HttpServletRequest request){
    	sysUser.setDeleteFlag((byte)(0));
    	sysUser.setUpdateTime(new Date());
    	sysUser.setUpdateUserId(((SysUser)request.getSession().getAttribute(Constant.SESSION_SYS_USER)).getId());//获取当前登录用户id
    	if(null==sysUser.getId()){
    		sysUser.setCreateTime(new Date());
        	sysUser.setCreateUserId(((SysUser)request.getSession().getAttribute(Constant.SESSION_SYS_USER)).getId());//获取当前登录用户id
        	sysUser.setPassword(DigestUtils.md5Hex(sysUser.getPassword()));
        	sysUserService.insert(sysUser);
    	}else{
    		sysUserService.updateByPrimaryKeySelective(sysUser);
    	}
        return RetMsg.createRetMsg("true", "保存成功");
    }
}
