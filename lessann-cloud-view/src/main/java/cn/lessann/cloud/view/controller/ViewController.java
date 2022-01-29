package cn.lessann.cloud.view.controller;

import cn.lessann.cloud.api.user.UserApi;
import cn.lessann.cloud.beans.http.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO
 *
 * @author LessAnn
 * @version 1.0
 * @date 2022/1/29 11:34 下午
 */
@RestController
@RequestMapping("/view")
public class ViewController {

    @Autowired
    UserApi userApi;

    @GetMapping
    public ResponseEntity<Message> getUser() {
        Message list = userApi.list();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
