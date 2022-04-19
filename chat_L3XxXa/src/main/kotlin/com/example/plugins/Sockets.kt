package com.example.plugins

import com.example.*
import io.ktor.websocket.*
import io.ktor.server.application.*
import io.ktor.server.routing.*
import io.ktor.server.websocket.*
import java.time.*
import java.util.*
import kotlin.collections.LinkedHashSet

fun Application.configureSockets() {
    install(WebSockets) {
        pingPeriod = Duration.ofSeconds(15)
        timeout = Duration.ofSeconds(15)
        maxFrameSize = Long.MAX_VALUE
        masking = false
    }

    routing{
        val connections = Collections.synchronizedSet<Connection?>(LinkedHashSet())
        webSocket("/chat"){
            println("Connecting")
            val thisConnection = Connection(this)
            connections += thisConnection
            send("You are connected Total users:${connections.count()}")
            for(frame in incoming) {
                frame as? Frame.Text ?: continue
                val receivedText = frame.readText()
                val message = "User${thisConnection.name}: $receivedText"
                connections.forEach{
                    it.session.send(message)
                }
            }
        }
    }
}
