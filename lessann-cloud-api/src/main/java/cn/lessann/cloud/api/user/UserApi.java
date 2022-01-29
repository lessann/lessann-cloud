package cn.lessann.cloud.api.user;

import cn.lessann.cloud.api.config.FeignLogConfig;
import cn.lessann.cloud.api.user.hystrix.UserApiHystrix;
import cn.lessann.cloud.beans.http.Message;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * TODO
 *
 * @author LessAnn
 * @version 1.0
 * @date 2022/1/29 10:59 下午
 */
@RequestMapping("/user")
@FeignClient(value = "lessann-cloud-user", fallback = UserApiHystrix.class, configuration = FeignLogConfig.class)
public interface UserApi {

    @GetMapping("/list")
    Message list();

}
