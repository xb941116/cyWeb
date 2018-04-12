package com.zzwdkj.base.interceptor;

import com.zzwdkj.biz.entity.Biz;
import com.zzwdkj.common.BaseConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author guoxianwei 2015-08-13 10:31
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {

    private final Logger log = LoggerFactory.getLogger(LoginInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {

        log.info("==============执行顺序: 1、preHandle================");
        String requestUri = request.getRequestURI();
        String contextPath = request.getContextPath();
        String url = requestUri.substring(contextPath.length());

        log.info("requestUri:" + requestUri);
        log.info("contextPath:" + contextPath);
        log.info("url:" + url);

        Biz acct = (Biz) request.getSession().getAttribute(BaseConfig.SESSION_VAR);
        if (acct == null) {
            if ((contextPath+"/loginCtrl/login.json").equals(requestUri)||(contextPath+"/regCtrl/cases").equals(requestUri)||(contextPath+"/regCtrl/adoutUser").equals(requestUri)
                    ||(contextPath+"/regCtrl/login").equals(requestUri)||(contextPath+"/regCtrl/selAd").equals(requestUri)||(contextPath+"/regCtrl/submitAd").equals(requestUri)
                    ||(contextPath+"/regCtrl/toAdPay").equals(requestUri)||(contextPath+"/regCtrl/adPay").equals(requestUri)||(contextPath+"/regCtrl/confirmPay").equals(requestUri)
                    ||(contextPath+"/regCtrl/toDetails").equals(requestUri)) {
                return true;
            }
            log.info("Interceptor：跳转到login页面！");
            response.sendRedirect(contextPath+"/index.jsp");
            return false;
        } else {
            return true;
        }
    }
}
