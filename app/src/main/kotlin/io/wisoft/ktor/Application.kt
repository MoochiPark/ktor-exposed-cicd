package io.wisoft.ktor

import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.features.CORS
import io.ktor.features.CallLogging
import io.ktor.features.ContentNegotiation
import io.ktor.routing.routing
import io.ktor.serialization.json
import io.ktor.server.netty.EngineMain
import io.ktor.util.KtorExperimentalAPI
import io.wisoft.ktor.config.DatabaseInitializer.init
import io.wisoft.ktor.config.DatabaseInitializer.testInit
import io.wisoft.ktor.handler.CustomerHandler
import io.wisoft.ktor.routes.customer

fun main(args: Array<String>) = EngineMain.main(args)

@KtorExperimentalAPI
@JvmOverloads
fun Application.module(testing: Boolean = true) {
    install(CallLogging)
    install(ContentNegotiation) {
        json()
    }
    install(CORS) {
        anyHost()
    }
    routing {
        customer(CustomerHandler())
    }
    when {
        testing -> testInit()
        else -> init()
    }
}
