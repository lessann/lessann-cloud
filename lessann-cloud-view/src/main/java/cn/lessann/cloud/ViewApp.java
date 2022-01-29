package cn.lessann.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * TODO
 *
 * @author LessAnn
 * @version 1.0
 * @date 2022/1/29 11:32 下午
 */
@EnableFeignClients
@SpringBootApplication
@EnableDiscoveryClient
public class ViewApp {
    public static void main(String[] args) {
        SpringApplication.run(ViewApp.class, args);
    }
}
