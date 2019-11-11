package com.example.services

import com.example.models.User

interface UserService {
    fun getAllUsers(): List<User>
    fun getUserById(userId: Long): User?
    fun saveUser(user: User): User
}
