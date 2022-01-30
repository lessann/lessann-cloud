package cn.lessann.cloud.user.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.security.SecureRandom;

/**
 * TODO
 *
 * @author LessAnn
 * @version 1.0
 * @date 2022/1/30 7:12 下午
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "lessann.encoder")
public class PasswordConfig {

    private String secret;
    private int length;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        // 利用密钥生成随机安全码
        SecureRandom secureRandom = new SecureRandom(secret.getBytes());
        // 初始化BCryptPasswordEncoder
        return new BCryptPasswordEncoder(length, secureRandom);
    }
}
