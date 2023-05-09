package com.wjf.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author JunFei
 * @data
 * @purpose
 */

@SpringBootApplication
@EnableFeignClients // 开启feign客户端
@EnableDiscoveryClient // 开启EurekaClient功能
public class UserApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }

}
