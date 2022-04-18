package malov.nsu.ru.route
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.util.Date


fun Route.customerRouting() {
    route("/check") {
        get {
            call.respondText("I AM WORKING", status = HttpStatusCode.OK)
        }
    }
    route("/sayhello") {
        get {
            call.respondText("I don't want to talk with you", status = HttpStatusCode.OK)
        }
    }

    route("/responsehtml") {
        get {
            call.respondText("Hello with HTML", ContentType.Text.Html, status = HttpStatusCode.OK)
        }
    }
}

