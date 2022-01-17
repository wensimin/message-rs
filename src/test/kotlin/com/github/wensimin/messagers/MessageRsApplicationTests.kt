package com.github.wensimin.messagers

import com.github.wensimin.messagers.pojo.MessageVo
import org.junit.jupiter.api.Test
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class MessageRsApplicationTests {

    @Test
    fun contextLoads(@Autowired rabbitTemplate: RabbitTemplate) {
        rabbitTemplate.convertAndSend("message", MessageVo("title", "body", "full-user", null, "message", null))
    }

}
