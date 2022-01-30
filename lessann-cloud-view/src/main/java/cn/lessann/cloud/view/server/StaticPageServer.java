package cn.lessann.cloud.view.server;

import cn.lessann.cloud.api.user.UserApi;
import cn.lessann.cloud.beans.http.Message;
import cn.lessann.cloud.beans.tables.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * TODO
 *
 * @author LessAnn
 * @version 1.0
 * @date 2022/1/30 11:28 上午
 */
@Slf4j
@Service
public class StaticPageServer {

    @Autowired
    UserApi userApi;

    @Autowired
    SpringTemplateEngine templateEngine;

    public Map<String, Object> loadItemData() {
        // 查询user
        Message list = userApi.list();
        // 获得到的用户信息
        log.debug(list.getObj().toString());

        Map<String, Object> map = new HashMap<>();
        User user = new User();
        user.setId(10001);
        user.setUserName("llccxx");
        user.setName("刘慈欣");
        user.setEmail("llccxx@email.com");
        map.put("article", user);

        return map;
    }

    public void createItemHtml() throws Exception {
        // 上下文，准备模型数据
        Context context = new Context();
        // 调用之前写好的加载数据方法
        Map<String, Object> variables = loadItemData();

        User user = (User) variables.get("article");

        context.setVariables(Collections.unmodifiableMap(variables));
        // 准备文件路径
        File dir = new File("/Users/yujiangzhong/IdeaProjects/Java/lessann-cloud/lessann-cloud-view/src/main/resources/user");
        if (!dir.exists()) {
            if (!dir.mkdirs()) {
                // 创建失败，抛出异常
                log.error("【静态页服务】创建静态页目录失败，目录地址：{}", dir.getAbsolutePath());
                throw new Exception("");
            }
        }
        File filePath = new File(dir, user.getId() + ".html");
        // 准备输出流
        try (PrintWriter writer = new PrintWriter(filePath, "UTF-8")) {
            templateEngine.process("item", context, writer);
        } catch (IOException e) {
            log.error("【静态页服务】静态页生成失败，商品id：{}", user.getId(), e);
            throw new Exception("");
        }
    }

}
