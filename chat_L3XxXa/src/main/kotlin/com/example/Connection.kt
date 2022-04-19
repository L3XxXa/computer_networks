package com.example

import io.ktor.server.websocket.*
import io.ktor.websocket.*
import java.util.concurrent.atomic.AtomicInteger

class Connection(val session: DefaultWebSocketSession) {
    companion object{
        var lastId = AtomicInteger(0)
    }
    val name = "user${lastId.getAndIncrement()}"

}