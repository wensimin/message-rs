package com.github.wensimin.messagers.controller

import com.github.wensimin.messagers.entity.Topic
import com.github.wensimin.messagers.pojo.TopicVo
import com.github.wensimin.messagers.service.TopicService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("client")
class ClientController(private val topicService: TopicService) {

    @PostMapping("addTopic")
    fun addTopic(@RequestBody @Valid topic: TopicVo): Topic {
        return topicService.create(topic)
    }
}