package com.github.wensimin.messagers.entity

import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.annotations.Type
import javax.persistence.Id
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Lob
import javax.validation.constraints.NotEmpty

@Entity
class Token(
    @Id
    @field:NotEmpty
    var id: String?,
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Column(nullable = false)
    var username: String?,
    @field:NotEmpty
    @Column(nullable = false)
    @Type(type = "org.hibernate.type.TextType")
    @Lob
    var deviceMessage: String?
) : Data()