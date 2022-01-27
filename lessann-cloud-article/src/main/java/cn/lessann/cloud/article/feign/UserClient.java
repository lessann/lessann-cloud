package cn.lessann.cloud.article.feign;

import cn.lessann.cloud.article.config.FeignLogConfig;
import cn.lessann.cloud.article.feign.hystrix.UserClientHystrix;
import cn.lessann.cloud.beans.http.Message;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * TODO
 *
 * @author LessAnn
 * @version 1.0
 * @date 2022/1/27 1:12 下午
 */
@FeignClient(value = "lessann-cloud-user", fallback = UserClientHystrix.class, configuration = FeignLogConfig.class)
public interface UserClient {

    /**
     * 服务器：lessann-cloud-user
     * 类：UserControllrt
     * 方法：public Message list()
     *
     * @return http通用方法
     */
    @GetMapping("/user/list")
    Message list();

}
