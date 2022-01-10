package com.github.wensimin.messagers.service

import com.github.wensimin.messagers.dao.TopicDao
import com.github.wensimin.messagers.entity.Topic
import com.github.wensimin.messagers.pojo.TopicVo
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.lang.RuntimeException

@Service
class TopicService(private val topicDao: TopicDao) {

    fun findAll(): List<Topic> {
        return topicDao.findAll()
    }

    fun register(topicId: String, name: String) {
        val topic = topicDao.findByIdOrNull(topicId) ?: throw RuntimeException("topic不存在")
        topic.registerUsers.add(name)
        topicDao.save(topic)
    }

    fun create(vo: TopicVo) {
        val newTopic = topicDao.findByIdOrNull(vo.id) ?: Topic(vo.id, vo.description)
        topicDao.save(newTopic)
    }


}