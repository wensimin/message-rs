package com.github.wensimin.messagers.controller

import com.github.wensimin.messagers.service.FirebaseService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("test")
class TestController(private val firebaseService: FirebaseService) {

    @GetMapping
    fun test() = firebaseService.test()
}