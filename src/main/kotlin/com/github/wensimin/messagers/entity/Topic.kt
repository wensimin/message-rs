package com.github.wensimin.messagers.entity

import javax.persistence.*

@Entity
class Topic(
    @Id
    var id: String?,
    @Column(nullable = false)
    var description: String?,
    @ElementCollection(fetch = FetchType.EAGER)
    var registerUsers: MutableSet<String> = mutableSetOf()
) : Data()