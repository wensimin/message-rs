package com.github.wensimin.messagers.pojo

import com.google.firebase.messaging.AndroidConfig
import javax.validation.constraints.NotEmpty

/**
 * message对象
 * mq 中使用该对象json string
 */
data class MessageVo(
    @field:NotEmpty
    var title: String?,
    @field:NotEmpty
    var body: String?,
    var toUser: String? = null,
    var toTopic: String? = null,
    var fromClient: String? = null,
    var url: String? = null,
    val priority: AndroidConfig.Priority = AndroidConfig.Priority.NORMAL
)