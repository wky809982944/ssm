package com.wangkaiyi.exception;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 全局异常处理器
 */
public class CustomerExceptionResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e) {
        String result = "系统发生异常请联系管理员";
        if(e instanceof  MyException){
            result = ((MyException) e).getMsg();
        }
        ModelAndView mav = new ModelAndView();
        mav.addObject("msg", result);
        mav.setViewName("msg");
        return mav;
    }
}
