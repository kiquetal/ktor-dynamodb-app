package me.cresterida.routes

import clients.Clients
import com.google.gson.Gson
import ddl.UserCreatorTable
import entities.Entity
import entities.User
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*
import org.slf4j.LoggerFactory
import repository.UserRepo
import java.time.Instant

fun Routing.myRoutes() {

    val l = LoggerFactory.getLogger(this::class.java)
    get("/kiquetal") {

    //   val creator =  UserCreatorTable()
    //    creator.createTable()

        val entity=Entity(pk = "ba405",sk = "firefighter",date = Instant.now())
        UserRepo(Clients.enhancedDynamoClient(Clients.dynamoLocalClient())).addEntity(entity)
        call.respond(listOf(User("kiquetal-soy-yo", 23)))
    }
}
