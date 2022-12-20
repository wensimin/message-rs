package com.github.wensimin.messagers.entity

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

@Entity
class Message(
    @Id
    var id: String,
    @Column(nullable = false)
    var title: String,
    @Column(nullable = false, length = 1024)
    var body: String,
    @Column(nullable = false)
    var toUser: String,
    var toTopic: String?,
    @Column(nullable = false)
    var fromClient: String,
    var url: String?,
    @Column(nullable = false)
    var error: Boolean = false
) : Data()