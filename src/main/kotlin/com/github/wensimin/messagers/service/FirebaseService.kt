package com.github.wensimin.messagers.service

import com.google.api.client.http.HttpTransport
import com.google.api.client.http.javanet.NetHttpTransport
import com.google.auth.http.HttpTransportFactory
import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.Message
import com.google.firebase.messaging.Notification
import org.slf4j.Logger
import org.springframework.stereotype.Service
import org.springframework.util.ResourceUtils
import java.net.InetSocketAddress
import java.net.Proxy


@Service
class FirebaseService(private val logger: Logger) {
    private val app: FirebaseApp

    init {
        System.setProperty("https.proxyHost", "127.0.0.1")
        System.setProperty("https.proxyPort", "1080")
        System.setProperty("com.google.api.client.should_use_proxy", "true")
//        val proxy = Proxy(Proxy.Type.HTTP, InetSocketAddress(, ))
//        val httpTransport = NetHttpTransport.Builder().setProxy(proxy).build();
        val options = FirebaseOptions.builder()
            .setCredentials(
                GoogleCredentials.fromStream(
                    ResourceUtils.getFile("classpath:shali-fcm-firebase-adminsdk-opn5v-18154bce89.json").inputStream()
                )
            )
//            .setHttpTransport(httpTransport)
            .build()
        app = FirebaseApp.initializeApp(options)
    }

    fun test() {
//        val registrationToken =
//            "ej_w0m6mRtK-jRWSYiHPFT:APA91bFjPzRgn_RSGBqYxUVojP_N2FxqZ2pbDscM3abujeO0KlxVr5INulcrp_atMb4UpYvLuvrK1zXJQoAuFFJjcYRwd37l7H0JwcmGXF01gb9v2oDoEW9ju-SzHFWp8j7aws_-L3bj"
        val registrationToken =
            "eSTczUPnTaGmjsQy48Tuhb:APA91bGpopnd3YutSLS72D_qi9kqGAmSzcQ_GLyuI3ejHamp2ZpLz0hbk08XXZrspA1vk-Er4xeT-PHuh_LsZms03YlZTxnjPFzEZO3_WOSs8IiNXzW9tQTG487CUUOBxmDf2W-w-fYv"
        val message = Message.builder()
            .putData("title", "title").putData("body", "body")
            .setToken(registrationToken)
            .build()
        val response = FirebaseMessaging.getInstance().send(message)
        logger.debug(response)
    }


}