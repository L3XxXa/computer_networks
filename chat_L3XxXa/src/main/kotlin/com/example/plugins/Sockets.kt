package com.example.plugins

import com.example.Connection
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.routing.*
import io.ktor.server.websocket.*
import io.ktor.websocket.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.Duration
import java.util.*
import kotlin.collections.LinkedHashSet
import kotlin.system.exitProcess

fun Application.configureSockets() {
    install(WebSockets) {
        pingPeriod = Duration.ofSeconds(15)
        timeout = Duration.ofSeconds(15)
        maxFrameSize = Long.MAX_VALUE
        masking = false
    }

    routing {
        val connections = Collections.synchronizedSet<Connection?>(LinkedHashSet())
        webSocket("/") {
            var privateMessage = LinkedHashSet<Connection>()
            val name = call.parameters["name"].toString()
            var toUser: Connection? = null
            println("Connecting $name")
            val thisConnection = Connection(this, call.parameters["name"].toString())
            connections += thisConnection
            try {
                send("You are connected Total users:${connections.count()}")
                for (frame in incoming) {
                    frame as? Frame.Text ?: continue
                    val receivedText = frame.readText()
                    var message = "${thisConnection.name}: $receivedText"
                    connections.forEach{
                        if (receivedText.contains("@" + it.name)){
                            privateMessage.add(it)
                            val messageWithoutName = receivedText.replace("@${it.name}", "")
                            message = "By ${thisConnection.name}: $messageWithoutName"
                        }
                    }
                    if (privateMessage.isEmpty()) {
                        connections.forEach{
                            it.session.send(message)
                        }
                    }
                    else {
                        privateMessage.forEach{
                            it.session.send(message)
                        }
                        privateMessage.clear()
                    }
                }
            } catch (e: Exception) {
                println("Server was stopped")
            }
            finally {
                println("Logging out user ${thisConnection.name}")
                connections -= thisConnection
            }
        }
    }
}


