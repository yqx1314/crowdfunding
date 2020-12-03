package com.suning.crowdfunding.controller;

import com.suning.crowdfunding.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

/**
 * @author yqx
 * @Company https://www.suning.com
 * @date 2020/11/30 12:56
 * @desc
 */
@Controller
@RequestMapping("/test")
public class TestController {
    @Autowired
    private UserService userService;
    @RequestMapping("/index")
    public String index(){
        return "success";
    }
    @ResponseBody
    @RequestMapping("/json")
    public Object json(){
        HashMap<String,String> map = new HashMap<String,String>();
        map.put("username","yqx");
        return map;
    }
    @ResponseBody
    @RequestMapping("/queryAll")
    public Object queryAll(){
        return userService.queryAll();
    }
}
