package com.github.wensimin.messagers.pojo

import javax.persistence.Column
import javax.validation.constraints.NotEmpty

class TopicVo(
    @NotEmpty
    var id: String?,
    @Column(nullable = false)
    @NotEmpty
    var description: String?
)