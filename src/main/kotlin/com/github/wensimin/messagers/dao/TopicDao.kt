package com.github.wensimin.messagers.dao

import com.github.wensimin.messagers.entity.Topic
import com.github.wensimin.messagers.pojo.TopicProjection
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface TopicDao : JpaRepository<Topic, String> {

    @Query(
        nativeQuery = true,
        value = "select t.*,(select count(*)>0 from topic_register_users tr where tr.topic_id=t.id and tr.register_users=:username) subscribed  from topic t"
    )
    fun findProjection(username: String): List<TopicProjection>

}