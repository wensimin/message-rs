package com.github.wensimin.messagers.entity

import java.time.Instant
import javax.persistence.Column
import javax.persistence.EntityListeners
import javax.persistence.MappedSuperclass
import javax.persistence.PreUpdate

@MappedSuperclass
@EntityListeners(DataEntityListener::class)
open class Data(
    @Column(nullable = false)
    val createDate: Instant = Instant.now(),
    @Column(nullable = false)
    var updateDate: Instant = Instant.now()
) {
    fun beforeUpdate() {
        updateDate = Instant.now()
    }
}


class DataEntityListener {
    @PreUpdate
    fun methodExecuteBeforeUpdate(reference: Data) {
        reference.beforeUpdate()
    }
}
