package cn.lessann.cloud.user.controller;

import cn.lessann.cloud.beans.http.Message;
import cn.lessann.cloud.beans.tables.User;
import cn.lessann.cloud.user.server.UserService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

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

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/list")
    public Message list() {
        List<User> allUser = userService.getAllUser();
        return new Message("all user list", allUser);
    }

    @ApiOperation(value = "校验用户名数据是否可用，如果不存在则可用")
    @ApiResponses({
            @ApiResponse(code = 200, message = "校验结果有效，true或false代表可用或不可用"),
            @ApiResponse(code = 400, message = "请求参数有误，比如type不是指定值")
    })
    @PostMapping
    public Message register(@Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            String msg = result.getFieldErrors().stream().map(FieldError::getDefaultMessage)
                    .collect(Collectors.joining("|"));
            return new Message(msg, false);
        }

        if (user != null) {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        }

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