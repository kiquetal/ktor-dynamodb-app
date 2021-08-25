package me.cresterida.routes

import clients.Clients
import clients.Repos
import com.google.gson.Gson
import ddl.UserCreatorTable
import entities.Entity
import entities.User
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import org.slf4j.LoggerFactory
import repository.UserRepo
import java.time.Instant

fun Routing.myRoutes() {

    val l = LoggerFactory.getLogger(this::class.java)
    get("/create") {

       val creator =  UserCreatorTable()
        creator.createTable()

        call.respond(listOf(User("kiquetal-soy-yo", 23)))
    }
    get("/lista") {

        call.respond(UserRepo(Clients.enhancedDynamoClient(Clients.dynamoLocalClient()))
            .getAllEntities())
    }
    get("/add")
    {
        val u = Entity(pk = "kiquetal-6", sk = "12", Instant.now())
        call.respond(Repos.userRepo().addEntity(u))
    }

}
