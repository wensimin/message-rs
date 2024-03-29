package com.github.wensimin.messagers.service

import com.github.wensimin.messagers.config.ProxyConfigProp
import com.google.api.client.http.javanet.DefaultConnectionFactory
import com.google.api.client.http.javanet.NetHttpTransport
import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.messaging.AndroidConfig
import com.google.firebase.messaging.BatchResponse
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.MulticastMessage
import org.slf4j.Logger
import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Service
import java.net.InetSocketAddress
import java.net.Proxy


@Service
class FirebaseService(
    private val logger: Logger,
    proxyConfigProp: ProxyConfigProp
) {
    private val app: FirebaseApp
    private val instance: FirebaseMessaging

    init {
        val proxy = Proxy(Proxy.Type.SOCKS, InetSocketAddress(proxyConfigProp.host!!, proxyConfigProp.port!!))
        val httpTransport = NetHttpTransport.Builder()
            .setProxy(proxy)
            .setConnectionFactory(
                DefaultConnectionFactory(proxy)
            ).build()
        val options = FirebaseOptions.builder()
            .setCredentials(
                GoogleCredentials.fromStream(
                    ClassPathResource("shali-fcm-firebase-adminsdk-opn5v-18154bce89.json").inputStream
                ) { httpTransport }
            )
            .setConnectTimeout(5000)
            .setReadTimeout(5000)
            .setHttpTransport(httpTransport)
            .build()
        app = FirebaseApp.initializeApp(options)
        instance = FirebaseMessaging.getInstance(app)
    }

    /**
     * 对多个用户发送消息
     */
    fun sendMultiMessage(
        title: String?, body: String?, url: String?,
        toUsers: Collection<String>,
        priority: AndroidConfig.Priority = AndroidConfig.Priority.NORMAL
    ): BatchResponse {
        val data = mutableMapOf(
            "title" to title,
            "body" to body,
            "url" to url
        )
        logger.debug("send ${toUsers.size} message")
        // 设置消息重要程度
        val androidConfig = AndroidConfig.builder().setPriority(priority).build()
        // 清除可选参空值
        data.values.removeIf { it == null }
        return instance.sendMulticast(
            MulticastMessage.builder()
                .addAllTokens(toUsers.toList())
                .setAndroidConfig(androidConfig)
                .putAllData(data)
                .build()
        )
    }

}