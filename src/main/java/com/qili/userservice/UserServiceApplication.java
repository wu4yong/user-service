package com.qili.userservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class UserServiceApplication {
    /**
     * a 服务调用b 服务多个接口，响应时间最短的方案
     * <p>
     * a服务：user-service
     * b服务：other-service
     *
     * userService方式：接口串行方式，执行时间为调用接口时间之和
     * userServiceThread :多线程并行方式，执行时间为调用接口最长时间稍大
     */
    //控制层 访问地址：http://localhost:8080/main/user?userId=1
    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
