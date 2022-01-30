package cn.lessann.cloud.view.controller;

import cn.lessann.cloud.api.user.UserApi;
import cn.lessann.cloud.beans.http.Message;
import cn.lessann.cloud.beans.tables.User;
import cn.lessann.cloud.view.server.StaticPageServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * TODO
 *
 * @author LessAnn
 * @version 1.0
 * @date 2022/1/29 11:34 下午
 */
@Slf4j
@Controller
@RequestMapping("/view")
public class ViewController {

    @Autowired
    UserApi userApi;

    @Autowired
    StaticPageServer staticPageServer;

    @GetMapping
    @ResponseBody
    public ResponseEntity<Message> getUser() {
        Message list = userApi.list();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/user/{id}.html")
    public String userInfo(Model model, @PathVariable int id) {
        log.debug("get id: " + id);
        Message message = userApi.list();
        List<User> list = (List<User>) message.getObj();
        model.addAttribute("article", list.get(0));
        return "item";
    }

    @GetMapping("/static")
    public void staticPage() {
        try {
            staticPageServer.createItemHtml();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
