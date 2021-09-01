package me.cresterida.routes

import me.cresterida.clients.Repos
import me.cresterida.entities.Entity
import me.cresterida.entities.User
import io.ktor.application.*
import io.ktor.locations.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import me.cresterida.ddl.UserCreatorTable
import org.slf4j.LoggerFactory
import java.time.*

fun Routing.myRoutes() {
    val l = LoggerFactory.getLogger(this::class.java)
    get("/create") {
       val creator =  UserCreatorTable().createTable()
        call.respond(mapOf("response" to "OK"))
    }
    get<RepoList> {
            repoList->
            l.info("Some funny queryprmas ${repoList.count}")
        call.respond(Repos.entityRepo().getAllEntities())
    }
    get<RepoAdd> {
        repoAdd->
        val u = Entity(pk=repoAdd.pk, sk = repoAdd.sk, ZonedDateTime.now(ZoneId.of("UTC")))
        call.respond(Repos.entityRepo().addEntity(u))
    }
    get<EntityById> {
        entityById ->
                    val entityKey = entityById.entityId
            l.info(String.format("Buscar entity=%s ver-nuevo a", entityKey));
            call.respond(Repos.entityRepo().findEntityByPk(entityKey))
    }
    post("/users") {

        val body = call.receive<User>()

        try {
            Repos.userRepo().addEntity(body)
            call.respond(body)
        }
        catch (e:Exception)
        {
            call.respond(hashMapOf("error" to e.message))
        }
    }

}

@Location("/entity/{entityId}")
data class EntityById(val entityId:String, val page:Int, val count:Int)

@Location("/repo/add")
data class RepoAdd(val pk:String,val sk:String)

@Location("/repo/list")
data class RepoList(val count:Int?=10,val page:Int?=5)

