package com.wangkaiyi.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录拦截器
 */
public class LoginInterceptor implements HandlerInterceptor {
    //进入方法前被执行
    //登录拦截,权限校验
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object args) throws Exception {
        //判断用户有没有登录
        Object o = request.getSession().getAttribute("username");
        if (o == null) {
            response.sendRedirect(request.getContextPath()+"/user/toLogin.action");
        }
        System.out.println("Myinterceptor.preHandle");
        //true放行
        return true;
    }
    //方法执行之后，返回ModelAndView之前执行
    //设置页面的共用参数
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        System.out.println("Myinterceptor.postHandle");
    }
    //方法执行后被执行
    //处理异常,清理资源,处理日志
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        System.out.println("Myinterceptor.afterHandle");
    }
}
