package com.suning.crowdfunding.controller;

import com.suning.crowdfunding.domain.AJAXResult;
import com.suning.crowdfunding.domain.Permission;
import com.suning.crowdfunding.domain.User;
import com.suning.crowdfunding.service.PermissionService;
import com.suning.crowdfunding.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * @author yqx
 * @Company https://www.suning.com
 * @date 2020/11/30 13:51
 * @desc
 */
@Controller
public class DispatcherController {
    @Autowired
    private UserService userService;
    @Autowired
    private PermissionService permissionService;

    /**
     *跳转到登录页面
     * @return
     */
    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    /**
     * 重定向到主页面
     * @return
     */
    @RequestMapping("/main")
    public String main() {
        return "main";
    }

    /**
     * 退出系统，回到登录页面
     * @param session
     * @return
     */
    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:login";
    }

    /**
     * 执行登录
     * @return
     */
    @Deprecated
    @RequestMapping("/doLogin")
    public String doLogin(User user, Model model){
        User dbUser = userService.query4Login(user);
        // 3) 判断用户信息是否存在
        if ( dbUser != null ) {
            // 登陆成功，跳转到主页面
            return "main";
        } else {
            // 登陆失败，跳转回到登陆页面，提示错误信息
            String errorMsg = "登陆账号或密码不正确，请重新输入";
            model.addAttribute("errorMsg", errorMsg);
            return "redirect:login";
        }
    }

    /**
     * 异步调用登录
     * @param user
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping("/doAJAXLogin")
    public Object doAJAXLogin(User user, HttpSession session) {

        AJAXResult result = new AJAXResult();

        User dbUser = userService.query4Login(user);
        if (dbUser != null) {
            session.setAttribute("loginUser", dbUser);

            // 获取用户权限信息
            List<Permission> permissions = permissionService.queryPermissionsByUser(dbUser);
            Map<Integer, Permission> permissionMap = new HashMap<Integer, Permission>();
            Permission root = null;
            Set<String> uriSet = new HashSet<String>();
            for (Permission permission : permissions) {
                permissionMap.put(permission.getId(), permission);
                if (permission.getUrl() != null && !"".equals(permission.getUrl())) {
                    uriSet.add(session.getServletContext().getContextPath() + permission.getUrl());
                }
            }
            session.setAttribute("authUriSet", uriSet);
            for (Permission permission : permissions) {
                Permission child = permission;
                if (child.getPid() == 0) {
                    root = permission;
                } else {
                    Permission parent = permissionMap.get(child.getPid());
                    parent.getChildren().add(child);
                }
            }
            session.setAttribute("rootPermission", root);
            result.setSuccess(true);
        } else {
            result.setSuccess(false);
        }

        return result;
    }

    /**
     * 跳转到错误页面
     * @return
     */
    @RequestMapping("/error")
    public String error(){
        return "error";
    }
}
