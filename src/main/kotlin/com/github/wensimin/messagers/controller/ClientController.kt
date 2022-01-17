package com.github.wensimin.messagers.controller

import com.github.wensimin.messagers.entity.Topic
import com.github.wensimin.messagers.pojo.MessageVo
import com.github.wensimin.messagers.pojo.TopicVo
import com.github.wensimin.messagers.service.MessageService
import com.github.wensimin.messagers.service.TopicService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.security.Principal
import javax.validation.Valid

@RestController
@RequestMapping("client")
class ClientController(
    private val topicService: TopicService,
    private val messageService: MessageService
) {

    @PostMapping("addTopic")
    fun addTopic(@RequestBody @Valid topic: TopicVo): Topic {
        return topicService.create(topic)
    }

    @PostMapping("sendMessage")
    fun sendMessage(@RequestBody @Valid messageVo: MessageVo, principal: Principal) {
        messageVo.fromClient = principal.name
        messageService.sendMessage(messageVo)
    }
}