package com.wjf.car;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author JunFei
 * @data
 * @purpose
 */

@SpringCloudApplication
@EnableFeignClients // 开启feign客户端
@EnableTransactionManagement
public class CarApplication {

    public static void main(String[] args) {
        SpringApplication.run(CarApplication.class, args);
    }

}
