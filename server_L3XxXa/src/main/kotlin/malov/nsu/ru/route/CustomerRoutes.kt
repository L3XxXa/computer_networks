package malov.nsu.ru.route
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*


fun Route.customerRouting(){
    route("/check"){
        get {
            call.respondText("I AM WORKING", status = HttpStatusCode.OK)
        }
    }
    route("/sayhello"){
        get {
            call.respondText("I don't want to talk with you", status = HttpStatusCode.OK)
        }
    }
}

