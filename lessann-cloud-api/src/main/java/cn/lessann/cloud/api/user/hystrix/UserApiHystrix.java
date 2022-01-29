package cn.lessann.cloud.api.user.hystrix;

import cn.lessann.cloud.api.user.UserApi;
import cn.lessann.cloud.beans.http.Message;

/**
 * TODO
 *
 * @author LessAnn
 * @version 1.0
 * @date 2022/1/29 11:01 下午
 */
public class UserApiHystrix implements UserApi {
    @Override
    public Message list() {
        return new Message("查询用户信息失败", false);
    }
}
