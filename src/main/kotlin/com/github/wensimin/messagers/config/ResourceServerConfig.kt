package com.github.wensimin.messagers.config

import org.springframework.context.annotation.Bean
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain


@EnableWebSecurity
class ResourceServerConfig {

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain? {
        http
            .authorizeRequests().apply {
                //本rs全部请求均需要client权限
                anyRequest().hasAuthority("CLIENT")
            }.and()
            .oauth2ResourceServer()
            .jwt()
        return http.build()
    }

}