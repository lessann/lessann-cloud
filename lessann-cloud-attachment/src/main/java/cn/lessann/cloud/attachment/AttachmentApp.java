package cn.lessann.cloud.attachment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * TODO
 *
 * @author LessAnn
 * @version 1.0
 * @date 2022/1/26 7:26 下午
 */
@EnableDiscoveryClient
@SpringBootApplication
public class AttachmentApp {
    public static void main(String[] args) {
        SpringApplication.run(AttachmentApp.class, args);
    }
}
