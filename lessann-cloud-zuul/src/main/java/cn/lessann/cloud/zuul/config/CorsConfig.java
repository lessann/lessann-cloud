package cn.lessann.cloud.zuul.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.List;

/**
 * TODO
 *
 * @author LessAnn
 * @version 1.0
 * @date 2022/1/27 7:10 下午
 */
@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter(CorsProperties corsProperties) {
        //1.添加CORS配置信息
        CorsConfiguration config = new CorsConfiguration();
        //1) 允许的域,不要写*，否则cookie就无法使用了
        corsProperties.getAllowedOrigins().forEach(config::addAllowedOrigin);
        //2) 是否发送Cookie信息
        config.setAllowCredentials(corsProperties.getAllowedCredentials());
        //3) 允许的请求方式
        corsProperties.getAllowedMethods().forEach(config::addAllowedMethod);
        // 4）允许的头信息
        corsProperties.getAllowedHeaders().forEach(config::addAllowedHeader);
        // 5）配置有效期
        config.setMaxAge(corsProperties.getMaxAge());

        //2.添加映射路径，我们拦截一切请求
        UrlBasedCorsConfigurationSource configSource = new UrlBasedCorsConfigurationSource();
        configSource.registerCorsConfiguration(corsProperties.getFilterPath(), config);

        //3.返回新的CORSFilter.
        return new CorsFilter(configSource);
    }

}
