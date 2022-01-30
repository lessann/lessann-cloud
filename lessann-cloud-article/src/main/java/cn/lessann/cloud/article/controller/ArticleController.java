package cn.lessann.cloud.article.controller;

import cn.lessann.cloud.api.user.UserApi;
import cn.lessann.cloud.beans.http.Message;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO
 *
 * @author LessAnn
 * @version 1.0
 * @date 2022/1/26 9:32 下午
 */
@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    UserApi userApi;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @GetMapping("/{id}")
    @HystrixCommand(fallbackMethod = "errorResponse")
    public Message getArticle(@PathVariable int id) {
        if (id == 1) {
            throw new RuntimeException("xxxx");
        }
        return userApi.list();
    }

    @GetMapping("/sendRedis")
    public String sendRedis() {
        stringRedisTemplate.opsForValue().set("lessann", "lessann");
        return "success";
    }

    public Message errorResponse(int id) {
        return new Message("目前比较拥挤，稍后再试！");
    }
}
