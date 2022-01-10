package com.github.wensimin.messagers.controller

import com.github.wensimin.messagers.dao.TokenDao
import com.github.wensimin.messagers.entity.Token
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("token")
class TokenController(private val tokenDao: TokenDao) {
    @PutMapping("{id}")
    fun put(@PathVariable id: String, token: JwtAuthenticationToken) {
        tokenDao.save(Token(id, token.name))
    }

    @GetMapping
    fun getAll(token: JwtAuthenticationToken): List<Token> {
        return tokenDao.findByUsername(token.name)
    }

    @DeleteMapping("{id}")
    fun delete(@PathVariable id: String, token: JwtAuthenticationToken) {
        tokenDao.findByIdOrNull(id)?.let {
            if (it.username == token.name) tokenDao.delete(it)
        }
    }

}