package malov.nsu.ru.plugins

import io.ktor.server.routing.*
import io.ktor.server.application.*
import malov.nsu.ru.route.customerRouting

fun Application.configureRouting() {
    routing {
        customerRouting()
    }
}
