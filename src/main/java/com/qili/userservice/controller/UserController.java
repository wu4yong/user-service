package com.qili.userservice.controller;

import com.qili.userservice.service.UserService;
import com.qili.userservice.service.UserServiceThread;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.concurrent.ExecutionException;

/**
 * @Date: 2020/10/31
 * @Author: wuyong
 * @Description: myservice
 */
@Controller
@RequestMapping("/main")
public class UserController {

    @Autowired
    private UserService userService;

    //对外提供接口，串行的方式
    @RequestMapping(value = "/user")
    @ResponseBody
    public String getUserInfo(String userId){
        return userService.getUserInfo(userId).toString();
    }

    //接口聚合,并行的方式

    @Autowired
    private UserServiceThread userServiceThread;

    @RequestMapping(value = "/userThread")
    @ResponseBody
    public String getuserThreadInfo(String userId) throws ExecutionException, InterruptedException {
        return userServiceThread.getuserInfo(userId).toString();
    }



}
