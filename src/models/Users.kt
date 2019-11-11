package com.example.models

import org.jetbrains.exposed.dao.LongIdTable

data class User(var id: Long?, val fullName: String, val email: String)

object UserTable : LongIdTable("users") {
    val fullName = varchar("fullname", 50)
    val email = varchar("email", 50)
}
