package me.cresterida.routes

import com.google.gson.Gson
import ddl.UserCreatorTable
import entities.User
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*

fun Routing.myRoutes() {


    get("/kiquetal") {

        call.respond(listOf(User("kiquetal-soy-yo", 23)))
        UserCreatorTable().createTable()
    }
}
