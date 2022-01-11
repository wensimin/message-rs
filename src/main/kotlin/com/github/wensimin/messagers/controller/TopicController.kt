package com.github.wensimin.messagers.controller

import com.github.wensimin.messagers.pojo.TopicProjection
import com.github.wensimin.messagers.service.TopicService
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("topic")
class TopicController(private val topicService: TopicService) {


    @GetMapping
    fun getAll(token: JwtAuthenticationToken): List<TopicProjection> {
        return topicService.findList(token.name)
    }

    @PutMapping("{topic}")
    fun register(@PathVariable topic: String, token: JwtAuthenticationToken) {
        topicService.register(topic, token.name)
    }

    @DeleteMapping("{topic}")
    fun unRegister(@PathVariable topic: String, token: JwtAuthenticationToken) {
        topicService.unRegister(topic, token.name)
    }

}