package malov.nsu.ru.route
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import malov.nsu.ru.FileTraversal
import malov.nsu.ru.ReadFiles
import java.io.File
import java.util.Date


fun Route.customerRouting() {
    route("/check") {
        get {
            call.respondText("I AM WORKING", status = HttpStatusCode.OK)
        }
    }
    route("/sayHello") {
        get {
            call.respondText("I don't want to talk with you", status = HttpStatusCode.OK)
        }
    }

    route("/responseHtml") {
        get {
            call.respondText("Hello with HTML", ContentType.Text.Html, status = HttpStatusCode.OK)
        }
    }

    route("{name?}"){
        get{
            call.respondText("Hello, " + call.parameters["name"], status = HttpStatusCode.OK)
        }
    }

    route("/getFiles"){
        val fileTraversal = FileTraversal()
        val res: String = fileTraversal.showAllFiles()
        get {
            call.respondText(res, status = HttpStatusCode.OK)
        }
    }

    route("/getFile/{name?}"){
        get {
            val readFiles = ReadFiles()
            val res: String = readFiles.readFile(call.parameters["name"].toString())
            call.respondText(res, status = HttpStatusCode.OK)
        }
    }


}

