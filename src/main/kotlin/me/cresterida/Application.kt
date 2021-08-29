package me.cresterida

import io.ktor.server.engine.*
import io.ktor.server.netty.*
import me.cresterida.plugins.*

fun main() {
    embeddedServer(Netty, port = 8081 , host = "127.0.0.1",watchPaths = listOf("classes")) {
        configureRouting()
      //  configureSecurity()
        configureHTTP()
        configureSerialization()
        status()
    }.start(wait = true)
}
