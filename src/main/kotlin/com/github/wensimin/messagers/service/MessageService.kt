package com.github.wensimin.messagers.service

import com.github.wensimin.messagers.dao.MessageDao
import com.github.wensimin.messagers.dao.TokenDao
import com.github.wensimin.messagers.dao.TopicDao
import com.github.wensimin.messagers.entity.Message
import com.github.wensimin.messagers.pojo.MessageVo
import org.slf4j.Logger
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.lang.RuntimeException
import java.util.*
import javax.transaction.Transactional

@Service
class MessageService(
    private val firebaseService: FirebaseService,
    private val topicDao: TopicDao,
    private val tokenDao: TokenDao,
    private val logger: Logger,
    private val messageDao: MessageDao
) {
    @Transactional
    fun sendMessage(messageVo: MessageVo) {
        val targetUsers = mutableSetOf<String>()
        when {
            messageVo.toTopic != null -> {
                topicDao.findByIdOrNull(messageVo.toTopic)?.let {
                    targetUsers.addAll(it.registerUsers)
                }
            }
            messageVo.toUser != null -> {
                targetUsers.add(messageVo.toUser!!)
            }
            else -> throw RuntimeException("缺少发送目标参数")
        }
        if (targetUsers.isEmpty()) {
            logger.warn("没有可用的发送目标")
            return
        }
        val tokens: List<String> = tokenDao.findByUsernameIn(targetUsers).map {
            it.id!!
        }
        firebaseService.sendMultiMessage(messageVo, tokens)
        logger.info("send message to ${targetUsers.size} user ${tokens.size} token title ${messageVo.title}")
        //save message log性质
        saveMessage(messageVo, targetUsers)
    }

    private fun saveMessage(messageVo: MessageVo, targetUsers: MutableSet<String>) {
        val messages = targetUsers.map {
            Message(
                id = UUID.randomUUID().toString(),
                title = messageVo.title!!,
                body = messageVo.body!!,
                toUser = it,
                toTopic = messageVo.toTopic,
                fromClient = messageVo.fromClient!!,
                url = messageVo.url
            )
        }
        messageDao.saveAll(messages)
    }
}