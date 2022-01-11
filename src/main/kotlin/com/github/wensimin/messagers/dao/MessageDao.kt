package com.github.wensimin.messagers.dao

import com.github.wensimin.messagers.entity.Message
import org.springframework.data.jpa.repository.JpaRepository

interface MessageDao : JpaRepository<Message, String> {
    fun findByToUser(user: String): List<Message>
    fun findByToTopic(topic: String): List<Message>
    fun findByFromClient(client: String): List<Message>
}