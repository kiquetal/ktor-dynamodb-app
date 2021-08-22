package me.cresterida.routes

import entities.User
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*

fun Routing.myRoutes() {

    get("/kiquetal") {
        call.respond(User("kiquetal-soy-yo", 12))
    }
}
