package com.qili.userservice.service;


import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Service;

/**
 * @Date: 2020/10/31
 * @Author: wuyong
 * @Description: myservice
 */
@Service
public class UserService {

    @Autowired
    private RemoterService remoterService;

    public Object getUserInfo(String userId) {
        long t1 = System.currentTimeMillis();

        //调用服务获取用户基本信息
        String uInfo = remoterService.getUserInfo(userId);//假设调用时间为3秒
        JSONObject userInfo = JSONObject.parseObject(uInfo);//{1,2,3}

        //调用服务获取用户余额信息
        String uMoney = remoterService.getUserMoney(userId);//假设调用时间为5秒
        JSONObject userMoney = JSONObject.parseObject(uMoney);//{4,,5,6}

        JSONObject result = new JSONObject();
        result.putAll(userInfo);
        result.putAll(userMoney);

        System.out.println("执行总时间为：" + (System.currentTimeMillis() - t1));//结果调用时间应该为2个服务之和 3+5 秒

        return result;
    }

}
