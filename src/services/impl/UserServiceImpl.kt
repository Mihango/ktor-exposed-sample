package com.example.services.impl

import com.example.models.User
import com.example.repositories.UserRepository
import com.example.services.UserService

class UserServiceImpl constructor(private val userRepository: UserRepository) : UserService {
    override fun getAllUsers(): List<User> = userRepository.getAllUsers()
    override fun getUserById(userId: Long): User? = userRepository.getUserById(userId)
    override fun saveUser(user: User): User = userRepository.saveUser(user)

}
