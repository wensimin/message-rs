package com.github.wensimin.messagers.dao

import com.github.wensimin.messagers.entity.Token
import org.springframework.data.jpa.repository.JpaRepository

interface TokenDao : JpaRepository<Token, String> {

    fun findByUsername(username: String): List<Token>

    fun findByUsernameIn(usernames: Collection<String>): List<Token>
}