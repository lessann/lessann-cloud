package cn.lessann.cloud.search;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * TODO
 *
 * @author LessAnn
 * @version 1.0
 * @date 2022/1/27 9:11 下午
 */
@EnableFeignClients
@SpringCloudApplication
public class SearchApp {
    public static void main(String[] args) {
        SpringApplication.run(SearchApp.class, args);
    }
}
