package com.qili.userservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @Date: 2020/10/31
 * @Author: wuyong
 * @Description: myservice
 */
@Service
public class RemoterService {

    @Autowired
    private RestTemplate restTemplate;

    //获取用户基本信息
    public String getUserInfo(String userId) {
        long currentTimeMillis = System.currentTimeMillis();
        String result = restTemplate.getForObject("http://localhost:8088/user/getUserInfo?userId=" + userId, String.class);
        System.out.println("获取用户基本信息时间为：" + (System.currentTimeMillis() - currentTimeMillis)+"秒");
        return result;
    }

    //获取用户余额
    public String getUserMoney(String userId) {
        long currentTimeMillis = System.currentTimeMillis();
        String result = restTemplate.getForObject("http://localhost:8088/money/getMoneyInfo?userId=" + userId, String.class);
        System.out.println("获取用户余额时间为：" + (System.currentTimeMillis() - currentTimeMillis)+"秒");
        return result;
    }

}
