package com.github.wensimin.messagers.controller

import com.github.wensimin.messagers.entity.Topic
import com.github.wensimin.messagers.pojo.TopicVo
import com.github.wensimin.messagers.service.TopicService
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("topic")
class TopicController(private val topicService: TopicService) {


    @GetMapping
    fun getAll(token: JwtAuthenticationToken): List<Topic> {
        return topicService.findAll()
    }

    @PutMapping("{topic}")
    fun register(@PathVariable topic: String, token: JwtAuthenticationToken) {
        topicService.register(topic, token.name)
    }

    @PostMapping
    fun create(@RequestBody @Validated topic: TopicVo) {
        topicService.create(topic)
    }
}