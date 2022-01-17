package com.github.wensimin.messagers.mq

import com.github.wensimin.messagers.pojo.MessageVo
import com.github.wensimin.messagers.service.MessageService
import org.slf4j.Logger
import org.springframework.amqp.rabbit.annotation.RabbitHandler
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Component


@Component
@RabbitListener(queues = ["message"])
class MessageReceiver(
    private val messageService: MessageService,
    private val logger: Logger
) {

    @RabbitHandler
    fun process(message: MessageVo) {
        logger.info("rabbitmq Receiver  : $message")
        try {
            messageService.sendMessage(message)
        } catch (e: Exception) {
            e.printStackTrace()
            //TODO 分开处理错误,归为send before after
            logger.error("处理message错误 ${e.message} $message")
        }
    }
}