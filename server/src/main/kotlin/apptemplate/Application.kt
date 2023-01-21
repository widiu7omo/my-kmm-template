package apptemplate

import io.ktor.server.application.*
import apptemplate.plugins.*

fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // application.conf references the main function. This annotation prevents the IDE from marking it as unused.
fun Application.module() {
    println("App run on env: ${if (this.environment.developmentMode) "DEVELOPMENT" else "PRODUCTION"}")
    configureSerialization()
    configureHTTP()
    configureSecurity()
    configureRouting()
}
