package cn.lessann.cloud.zuul.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * TODO
 *
 * @author LessAnn
 * @version 1.0
 * @date 2022/1/27 8:12 下午
 */
@Data
@Component
@ConfigurationProperties(prefix = "lessann.cors")
public class CorsProperties {
    private List<String> allowedOrigins;
    private Boolean allowedCredentials;
    private List<String> allowedMethods;
    private List<String> allowedHeaders;
    private Long maxAge;
    private String filterPath;
}
