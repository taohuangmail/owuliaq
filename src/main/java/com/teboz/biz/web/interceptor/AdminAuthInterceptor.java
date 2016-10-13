package com.teboz.biz.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.teboz.biz.model.SysUser;
import com.teboz.biz.web.constant.Constant;

public class AdminAuthInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        SysUser sysUser = (SysUser) session.getAttribute(Constant.SESSION_SYS_USER);
        if (sysUser != null) {
            return true;
        } else {
            if (request.getHeader("x-requested-with") != null
                    && request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")) {
                // 如果是ajax请求响应头会有，x-requested-with
                response.setHeader("sessionStatus", "timeout");// 在响应头设置session状态
                return false;
            }
            response.sendRedirect(request.getContextPath() + "/admin/toLogin.htm");
            return false;
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
    }

}
