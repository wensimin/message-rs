package com.github.wensimin.messagers.pojo

import javax.persistence.Column
import javax.validation.constraints.NotEmpty

class TopicVo(
    @field:NotEmpty
    var id: String?,
    @Column(nullable = false)
    @field:NotEmpty
    var description: String?
)