package cn.lessann.cloud.user.server.impl;

import cn.lessann.cloud.beans.tables.User;
import cn.lessann.cloud.user.dao.UserDao;
import cn.lessann.cloud.user.server.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * TODO
 *
 * @author LessAnn
 * @version 1.0
 * @date 2022/1/26 4:12 下午
 */
@Service
@Transactional(rollbackFor = {Exception.class})
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Override
    public List<User> getAllUser() {
        return userDao.getAllUser();
    }

    @Override
    public boolean userLogin(User user) {
        User loginUser = userDao.getUserByPasswordAndName(user);
        if (loginUser != null && loginUser.getUserName() != null) {
            return true;
        }
        return false;
    }

    @Override
    public boolean userRegister(User user) {
        return false;
    }

    @Override
    public User getUserById(int id) {
        return userDao.selectById(id);
    }

}
