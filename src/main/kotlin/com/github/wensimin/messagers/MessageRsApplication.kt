package com.github.wensimin.messagers

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.retry.annotation.EnableRetry

@SpringBootApplication
@EnableRetry
class MessageRsApplication

fun main(args: Array<String>) {
    runApplication<MessageRsApplication>(*args)
}
