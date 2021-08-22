package me.cresterida

import ddl.UserCreatorTable
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import me.cresterida.plugins.*

fun main() {
    embeddedServer(Netty, port = 8082, host = "0.0.0.0",watchPaths = listOf("classes")) {
        configureRouting()
      //  configureSecurity()
        configureHTTP()
        configureSerialization()
    }.start(wait = true)
}
