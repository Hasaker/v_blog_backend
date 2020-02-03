package com.hasaker.common;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @package com.hasaker.vblog
 * @author 余天堂
 * @create 2020/2/1 13:03
 * @description UserApplication
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class UserApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }
}
