package com.qili.userservice.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.*;

/**
 * @Date: 2020/10/31
 * @Author: wuyong
 * @Description: user-service
 */
@Service
public class UserServiceThread {

    @Autowired
    private RemoterService remoterService;

    //线程池
    static ExecutorService taskexe = Executors.newFixedThreadPool(10);

    //获取用户信息
    public Object getuserInfo(String userId) throws ExecutionException, InterruptedException {
        long t1 = System.currentTimeMillis();

        Callable<JSONObject> queryUserInfo = () -> {
            String uInfo = remoterService.getUserInfo(userId);//调用用户信息
            JSONObject userInfo = JSONObject.parseObject(uInfo);
            return userInfo;
        };

        Callable<JSONObject> queryMondyInfo = () -> {
            String moneyInfo = remoterService.getUserMoney(userId);//调用余额信息
            JSONObject userInfo = JSONObject.parseObject(moneyInfo);
            return userInfo;
        };

        FutureTask<JSONObject> userInfoTask = new FutureTask<>(queryUserInfo);
        FutureTask<JSONObject> moneyInfoTask = new FutureTask<>(queryMondyInfo);

        taskexe.submit(userInfoTask);
        taskexe.submit(moneyInfoTask);

        JSONObject result = new JSONObject();

        result.putAll(userInfoTask.get());//get是一个阻塞线程
        result.putAll(moneyInfoTask.get());
        System.out.println("执行多线程并行方式总时间为：" + (System.currentTimeMillis() - t1));//多线程并行执行结果为调用服务接口最长时间稍大
        return result;
    }
}
