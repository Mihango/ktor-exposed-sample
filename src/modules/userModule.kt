package com.example.modules

import com.example.models.User
import com.example.repositories.UserRepository
import com.example.services.impl.UserServiceImpl
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.route
import io.ktor.routing.routing

fun Application.userModule() {
    routing {
        val userService = UserServiceImpl(UserRepository())

        route("/users") {

            get {
                call.respond(userService.getAllUsers())
            }

            post {
                val user = call.receive<User>()
                call.respond(userService.addUser(user))
            }
        }
    }
}
