package com.github.wensimin.messagers.controller

import com.github.wensimin.messagers.dao.TokenDao
import com.github.wensimin.messagers.entity.Token
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("token")
class TokenController(private val tokenDao: TokenDao) {

    @PutMapping
    fun put(@Valid @RequestBody token: Token, user: JwtAuthenticationToken) {
        tokenDao.save(token.apply {
            username = user.name
        })
    }

    @GetMapping
    fun getAll(token: JwtAuthenticationToken): List<Token> {
        return tokenDao.findByUsername(token.name)
    }

    @GetMapping("{id}")
    fun get(@PathVariable id: String): Token? {
        return tokenDao.findByIdOrNull(id)
    }

    @DeleteMapping("{id}")
    fun delete(@PathVariable id: String, token: JwtAuthenticationToken) {
        tokenDao.findByIdOrNull(id)?.let {
            if (it.username == token.name) tokenDao.delete(it)
        }
    }

}