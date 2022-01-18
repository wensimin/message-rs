package com.github.wensimin.messagers.mq

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
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
    private val objectMapper: ObjectMapper,
    private val logger: Logger
) {

    @RabbitHandler
    fun process(message: String) {
        logger.info("rabbitmq Receiver  : $message")
        try {
            val messageVo: MessageVo = objectMapper.readValue(message)
            messageService.sendMessage(messageVo)
        } catch (e: Exception) {
            //目前消息成功与否都进行消费
            logger.error("处理message错误 ${e.stackTraceToString()} $message")
        }
    }
}