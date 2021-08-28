package me.cresterida.plugins

import com.google.gson.*
import io.ktor.gson.*
import io.ktor.features.*
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.request.*
import io.ktor.routing.*
import java.lang.reflect.Type
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

fun Application.configureSerialization() {
    install(ContentNegotiation) {
        gson {
            setPrettyPrinting()

            registerTypeAdapter(ZonedDateTime::class.java,object:JsonSerializer<ZonedDateTime>
            {


                override fun serialize(
                    src: ZonedDateTime?,
                    typeOfSrc: Type?,
                    context: JsonSerializationContext?
                ): JsonElement {

                    val dt = src?.withZoneSameInstant(ZoneId.of("America/Asuncion"))
                   return JsonPrimitive(dt?.format(DateTimeFormatter.ISO_DATE_TIME))
                }

            })
        }

    }
}
