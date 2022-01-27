package cn.lessann.cloud.article.feign.hystrix;

import cn.lessann.cloud.article.feign.UserClient;
import cn.lessann.cloud.beans.http.Message;
import org.springframework.stereotype.Component;

/**
 * TODO
 *
 * @author LessAnn
 * @version 1.0
 * @date 2022/1/27 1:29 下午
 */
@Component
public class UserClientHystrix implements UserClient {
    @Override
    public Message list() {
        return new Message("查询用户信息失败", false);
    }
}
