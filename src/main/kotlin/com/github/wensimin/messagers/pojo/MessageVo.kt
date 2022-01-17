package com.github.wensimin.messagers.pojo

import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.constraints.NotEmpty

data class MessageVo(
    @field:NotEmpty
    var title: String?,
    @field:NotEmpty
    var body: String?,
    var toUser: String?,
    var toTopic: String?,
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    var fromClient: String?,
    var url: String?
)