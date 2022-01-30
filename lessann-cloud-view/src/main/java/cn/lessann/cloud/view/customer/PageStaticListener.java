package cn.lessann.cloud.view.customer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * TODO
 *
 * @author LessAnn
 * @version 1.0
 * @date 2022/1/30 4:32 下午
 */
@Slf4j
@Component
public class PageStaticListener {

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "lessann.cloud.view.static.page", durable = "true"),
            exchange = @Exchange(
                    value = "lessann.cloud.exchange",
                    ignoreDeclarationExceptions = "true",
                    type = ExchangeTypes.TOPIC
            ),
            key = {"#.#"}))
    public void listen(String msg) {
        System.out.println("接收到消息：" + msg);
        log.debug("收到的队列消息：" + msg);
    }

}
