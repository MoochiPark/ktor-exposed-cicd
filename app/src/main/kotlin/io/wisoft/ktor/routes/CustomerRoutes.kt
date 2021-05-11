package io.wisoft.ktor.routes

import io.ktor.application.call
import io.ktor.http.HttpStatusCode.Companion.Accepted
import io.ktor.http.HttpStatusCode.Companion.BadRequest
import io.ktor.http.HttpStatusCode.Companion.Created
import io.ktor.http.HttpStatusCode.Companion.NoContent
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.response.respondText
import io.ktor.routing.Routing
import io.ktor.routing.delete
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.put
import io.ktor.routing.route
import io.ktor.util.KtorExperimentalAPI
import io.wisoft.ktor.dto.CustomerRequest
import io.wisoft.ktor.handler.CustomerHandler

@KtorExperimentalAPI
fun Routing.customer(handler: CustomerHandler) {
    route("customers") {
        get {
            call.respond(handler.getAll())
        }
        get("{id}") {
            val id = call.parameters["id"]?.toLongOrNull()
                ?: return@get call.respondText(
                    "Parameter id is null",
                    status = BadRequest
                )
            call.respond(handler.getById(id))
        }
        post {
            val request = call.receive<CustomerRequest>()
            handler.new(request)
            call.respondText("Customer stored correctly", status = Created)
        }
        put("{id}") {
            val id = call.parameters["id"]?.toLongOrNull()
                ?: return@put call.respondText(
                    "Parameter id is null",
                    status = BadRequest
                )
            val request = call.receive<CustomerRequest>()
            handler.renew(id, request)
            call.respond(NoContent)
        }
        delete("{id}") {
            val id = call.parameters["id"]?.toLongOrNull()
                ?: return@delete call.respondText(
                    "Parameter id is null",
                    status = BadRequest
                )
            handler.delete(id)
            call.respond(Accepted)
        }
    }
}
