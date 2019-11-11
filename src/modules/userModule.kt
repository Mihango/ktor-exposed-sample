package com.example.modules

import com.example.models.User
import com.example.repositories.UserRepository
import com.example.services.impl.UserServiceImpl
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.routing

fun Application.userModule() {
    routing {

//        intercept(ApplicationCallPipeline.Call) {
//            call.respond(ResponseWrapper<Any>(data = call.response.pipeline.attributes))
//        }
        val userService = UserServiceImpl(UserRepository())


        get("/users") {
            call.respond(userService.getAllUsers())
        }

        post("/users") {
            val user = call.receive<User>()
            call.respond(userService.saveUser(user))
        }

        get("/users/{id}") {
            val id = call.parameters["id"]

            if (id == null) {
                call.respond(HttpStatusCode.BadRequest, mapOf("message" to "id parameter is required"))
                return@get
            }

            val user = userService.getUserById(id.toLong())

            if (user != null) {
                call.respond(user)
            } else {
                call.respond(HttpStatusCode.NotFound, mapOf("message" to "user does not exist"))
            }

            call.respond("hello")
        }

    }
}
