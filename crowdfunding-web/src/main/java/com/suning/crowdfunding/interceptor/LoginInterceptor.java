package com.suning.crowdfunding.interceptor;

import com.suning.crowdfunding.domain.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author yqx
 * @Company https://www.suning.com
 * @date 2020/12/3 9:07
 * @desc
 */
public class LoginInterceptor implements HandlerInterceptor {
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        // 判断当前用户是否已经登陆
        HttpSession session = httpServletRequest.getSession();
        User loginUser = (User)session.getAttribute("loginUser");

        if ( loginUser == null ) {
            String path = session.getServletContext().getContextPath();
            httpServletResponse.sendRedirect(path + "/login");
            return false;
        } else {
            return true;
        }
    }

    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
