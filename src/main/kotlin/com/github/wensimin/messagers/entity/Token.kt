package com.github.wensimin.messagers.entity

import javax.persistence.Id
import javax.persistence.Column
import javax.persistence.Entity

@Entity
class Token(
    @Id
    var id: String?,
    @Column(nullable = false)
    var username: String?
) : Data()