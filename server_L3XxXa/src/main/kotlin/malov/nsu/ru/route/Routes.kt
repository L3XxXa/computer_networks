package malov.nsu.ru.route
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import malov.nsu.ru.FileTraversal
import malov.nsu.ru.ReadFiles


fun Route.customerRouting() {
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

    route(""){
        var name : String
        get{
            name = call.parameters["name"].toString()
            call.respondText("Hello, $name", status = HttpStatusCode.OK)
        }
    }

    route("/file/{dir}") {
        get {
            val fileTraversal = FileTraversal()
            val res = fileTraversal.showAllFiles(call.parameters["dir"].toString())
            if (res == "") {
                call.respondText("No such directory", status = HttpStatusCode.NotFound)
            } else {
                call.respondText(res, status = HttpStatusCode.OK)
            }
        }
    }

    route("/file/{dir?}/{filename?}"){
        get {
            val readFiles = ReadFiles()
            val res: String = readFiles.readFile(call.parameters["dir"].toString(), call.parameters["filename"].toString())
            call.respondText(res, status = HttpStatusCode.OK)
        }
    }


}

