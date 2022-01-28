package cn.lessann.cloud.user.controller;

import cn.lessann.cloud.beans.http.Message;
import cn.lessann.cloud.beans.tables.User;
import cn.lessann.cloud.user.server.UserService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * TODO
 *
 * @author LessAnn
 * @version 1.0
 * @date 2022/1/26 3:11 下午
 */
@RestController
@RequestMapping("/user")
@DefaultProperties(defaultFallback = "errorResponse")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/list")
    public Message list() {
        List<User> allUser = userService.getAllUser();
        return new Message("all user list", allUser);
    }

    @PostMapping
    public Message register(User user) {
        if (userService.userRegister(user)) {
            return new Message("success");
        }
        return new Message("register error", false);
    }

    @PutMapping
    public Message updateUser(User user) {
        return null;
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable int id) {
        User user = userService.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    public Message errorResponse(int id) {
        return new Message("目前比较拥挤，稍后再试！");
    }
}
