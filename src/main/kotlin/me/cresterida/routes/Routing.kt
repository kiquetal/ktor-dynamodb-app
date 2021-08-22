package me.cresterida.routes

import com.google.gson.Gson
import ddl.UserCreatorTable
import entities.User
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*
import org.slf4j.LoggerFactory

fun Routing.myRoutes() {

    val l = LoggerFactory.getLogger(this::class.java)
    get("/kiquetal") {

       val creator =  UserCreatorTable()
        creator.createTable()

        call.respond(listOf(User("kiquetal-soy-yo", 23)))
    }
}
