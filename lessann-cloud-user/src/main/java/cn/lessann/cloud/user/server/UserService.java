package cn.lessann.cloud.user.server;

import cn.lessann.cloud.beans.tables.User;

import java.util.List;

/**
 * TODO
 *
 * @author LessAnn
 * @version 1.0
 * @date 2022/1/26 4:11 下午
 */
public interface UserService {

    /**
     * 获得所有用户信息
     *
     * @return 用户信息集合
     */
    List<User> getAllUser();

    /**
     * 用户登录
     *
     * @param user 需要登录信息
     * @return 登录是否成功
     */
    boolean userLogin(User user);

    /**
     * 注册用户信息
     *
     * @param user 被注册用户信息
     * @return 是否注册成功
     */
    boolean userRegister(User user);

}
