package com.github.wensimin.messagers.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "proxy")
class ProxyConfigProp {
    var host: String? = null
    var port: Int? = null
}