package com.example.repositories

import com.example.models.User
import java.util.concurrent.atomic.AtomicInteger

class UserRepository {
    private val count = AtomicInteger()
    private val users = mutableListOf<User>()

    fun getAllUsers(): List<User> = users

    fun addUser(user: User): User {
        val c: Int = count.incrementAndGet()
        user.apply {
            this.id = c.toLong()
        }

        users.add(user)
        return user
    }

}
