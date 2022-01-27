package cn.lessann.cloud.article.config;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * TODO
 *
 * @author LessAnn
 * @version 1.0
 * @date 2022/1/26 7:19 下午
 */
@Configuration
public class BeanConfig {

    /**
     * 其他微服务器请求类
     *
     * @return 请求类对象
     */
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    /**
     * 改变负载均衡
     *
     * @return 负载均衡策略
     */
    @Bean
    public IRule randomRule() {
        return new RandomRule();
    }
}
