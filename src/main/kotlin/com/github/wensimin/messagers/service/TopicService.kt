package com.github.wensimin.messagers.service

import com.github.wensimin.messagers.dao.TopicDao
import com.github.wensimin.messagers.entity.Topic
import com.github.wensimin.messagers.pojo.TopicProjection
import com.github.wensimin.messagers.pojo.TopicVo
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.lang.RuntimeException

@Service
class TopicService(private val topicDao: TopicDao) {

    fun register(topicId: String, name: String) {
        val topic = topicDao.findByIdOrNull(topicId) ?: throw RuntimeException("topic不存在")
        topic.registerUsers.add(name)
        topicDao.save(topic)
    }

    fun create(vo: TopicVo): Topic {
        val newTopic = topicDao.findByIdOrNull(vo.id) ?: Topic(vo.id!!, vo.description!!)
        return topicDao.save(newTopic.apply {
            description = vo.description!!
        })
    }

    fun findList(name: String): List<TopicProjection> {
        return topicDao.findProjection(name)
    }

    fun unRegister(topicId: String, name: String) {
        val topic = topicDao.findByIdOrNull(topicId) ?: throw RuntimeException("topic不存在")
        topic.registerUsers.remove(name)
        topicDao.save(topic)
    }


}