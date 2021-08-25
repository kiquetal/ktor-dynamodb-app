package me.cresterida.routes

import me.cresterida.clients.Clients
import me.cresterida.clients.Repos
import me.cresterida.ddl.UserCreatorTable
import me.cresterida.entities.Entity
import me.cresterida.entities.User
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*
import org.slf4j.LoggerFactory
import me.cresterida.repository.UserRepo
import java.time.Instant

fun Routing.myRoutes() {
    val l = LoggerFactory.getLogger(this::class.java)
    get("/create") {

       val creator =  UserCreatorTable()
        creator.createTable()

        call.respond(listOf(User("kiquetal-soy-yo", 23)))
    }
    get("/lista") {

        call.respond(Repos.userRepo().getAllEntities())
    }
    get("/add")
    {
        val u = Entity(pk = "kiquetal-27", sk = "12", Instant.parse("2020-06-25T01:12:13.46702Z"))
        call.respond(Repos.userRepo().addEntity(u))
    }
    get("/entity/{entityId}") {

        val entityKey = call.parameters["entityId"]?:"no";
        l.info(String.format("Buscar entity=%s",entityKey));
        call.respond(Repos.userRepo().findEntityByPk(entityKey))



    }

}
