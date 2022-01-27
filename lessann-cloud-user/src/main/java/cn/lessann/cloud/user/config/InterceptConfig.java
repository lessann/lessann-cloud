package cn.lessann.cloud.user.config;

import cn.lessann.cloud.user.intercept.AuthIntercept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * TODO
 *
 * @author LessAnn
 * @version 1.0
 * @date 2022/1/26 8:03 下午
 */
@Configuration
public class InterceptConfig implements WebMvcConfigurer {

    @Autowired
    AuthIntercept authIntercept;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authIntercept).addPathPatterns("/**");
    }
}
