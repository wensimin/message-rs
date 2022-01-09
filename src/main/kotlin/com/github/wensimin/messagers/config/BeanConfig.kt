package com.github.wensimin.messagers.config

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.InjectionPoint
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Scope
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter


@Configuration
class BeanConfig {

    /**
     * 自定义转换jwt的权限
     */
    @Bean
    fun jwtAuthenticationConverter(): JwtAuthenticationConverter {
        val jwtAuthenticationConverter = JwtAuthenticationConverter()
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(JwtAuthConverter())
        return jwtAuthenticationConverter
    }

    /**
     * 注入logger
     */
    @Bean
    @Scope("prototype")
    fun logger(injectionPoint: InjectionPoint): Logger {
        return LoggerFactory.getLogger(
            injectionPoint.methodParameter?.containingClass // constructor
                ?: injectionPoint.field?.declaringClass // or field injection
        )
    }

}