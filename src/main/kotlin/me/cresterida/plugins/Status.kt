package me.cresterida.plugins
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.response.*
import me.cresterida.exceptions.Exception
import software.amazon.awssdk.services.dynamodb.model.ResourceNotFoundException
import java.lang.RuntimeException
fun Application.status()
{
    install(StatusPages)
    {

        status(HttpStatusCode.NotFound) {
            e-> call.respond(HttpStatusCode.InternalServerError,
                Exception(message = "Resource/does not exists",code = "5001"))
            log.info(e.description)
            throw Exception(e.toString())

        }

        exception<NotFoundException> {
            call.respond(HttpStatusCode.InternalServerError,
                Exception(message = "Error",code = "5004"))
           // throw  it
        }
        exception<ResourceNotFoundException> {
            call.respond(HttpStatusCode.InternalServerError,
                Exception(message = "Error",code = "5005"))


        }
        exception<Throwable> {
            exception->
            call.respond(HttpStatusCode.InternalServerError,
                Exception(message = "Error",code = "5004"))
            throw  exception
        }
    }
}
