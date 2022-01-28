package cn.lessann.cloud.user.dao;

import cn.lessann.cloud.beans.tables.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 用户相关数据库操作
 *
 * @author LessAnn
 * @version 1.0
 * @date 2022/1/26 4:08 下午
 */
@Mapper
public interface UserDao extends BaseMapper<User> {

    /**
     * 获得所有用户信息，包括失效的
     *
     * @return 所有用户集合
     */
    @Select("select * from User;")
    List<User> getAllUser();


    /**
     * 获得用户内容，通过用户名和密码
     *
     * @param user 需要查询用户信息
     */
    @Select("select * from User where name = #{name} and password = #{password} and status = 1")
    User getUserByPasswordAndName(User user);

    /**
     * 插入用户信息userName、password和email
     *
     * @param user 用户信息
     */
    @Insert("insert into User(`userName`, `password`, `email`) value(#{userName}, #{password}, #{email})")
    void insertUser(User user);
}
