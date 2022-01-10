package com.github.wensimin.messagers.dao

import com.github.wensimin.messagers.entity.Topic
import org.springframework.data.jpa.repository.JpaRepository

interface TopicDao : JpaRepository<Topic, String>