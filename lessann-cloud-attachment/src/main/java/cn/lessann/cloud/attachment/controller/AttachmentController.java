package cn.lessann.cloud.attachment.controller;

import cn.lessann.cloud.beans.http.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * TODO
 *
 * @author LessAnn
 * @version 1.0
 * @date 2022/1/26 7:27 下午
 */
@RestController
@RequestMapping("/attachment")
public class AttachmentController {

    @Autowired
    RestTemplate restTemplate;

    @GetMapping
    public Message getAttachment() {
        String url = "http://lessann-cloud-user/user/list";
        return restTemplate.getForObject(url, Message.class);
    }

}
