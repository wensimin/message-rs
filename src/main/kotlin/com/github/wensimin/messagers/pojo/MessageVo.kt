package com.github.wensimin.messagers.pojo

import com.fasterxml.jackson.annotation.JsonProperty
import java.io.Serializable
import javax.validation.constraints.NotEmpty

data class MessageVo(
    @field:NotEmpty
    var title: String?,
    @field:NotEmpty
    var body: String?,
    var toUser: String? = null,
    var toTopic: String? = null,
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    var fromClient: String? = null,
    var url: String? = null
) : Serializable