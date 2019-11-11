package com.example.services

import com.example.models.User

interface UserService {
    fun getAllUsers(): List<User>
    fun addUser(user: User): User
}
