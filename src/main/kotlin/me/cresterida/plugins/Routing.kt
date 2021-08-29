package me.cresterida.plugins

import io.ktor.routing.*
import io.ktor.http.*
import io.ktor.application.*
import io.ktor.locations.*
import io.ktor.response.*
import io.ktor.request.*
import me.cresterida.routes.myRoutes

fun Application.configureRouting() {

    install(Locations)
    {

    }

    routing {
        get("/") {
            call.respondText("Hello World que hay! ")
        }
        myRoutes()
    }
}
