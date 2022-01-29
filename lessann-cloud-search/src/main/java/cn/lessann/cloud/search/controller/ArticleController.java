package cn.lessann.cloud.search.controller;

import cn.lessann.cloud.beans.search.Article;
import cn.lessann.cloud.search.server.ArticleServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * TODO
 *
 * @author LessAnn
 * @version 1.0
 * @date 2022/1/29 10:08 下午
 */
@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    ArticleServer articleServer;

    @PostMapping
    public ResponseEntity<String> addDoc() {
        String php = "PHP又名超文本预处理器，是一种通用开源脚本语言。PHP主要适用于Web开发领域，语法吸收了C语言、Java和Perl的特点，利于学习，使用广泛。其独特的语法混合了C、Java、Perl以及PHP自创的语法。它可以比CGI或者Perl更快速地执行动态网页。用PHP做出的动态页面与其他的编程语言相比，PHP是将程序嵌入到HTML文档中去执行，执行效率比完全生成HTML标记的CGI要高许多；PHP还可以执行编译后代码，编译可以达到加密和优化代码运行，使代码运行更快。";

        Article article = new Article(5, "PHP基础", "少安", php, new Date());

        articleServer.articleAdd(article);

        return new ResponseEntity<>("保存成功", HttpStatus.OK);
    }

}
