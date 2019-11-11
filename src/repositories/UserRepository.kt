package com.example.repositories

import com.example.models.User
import com.example.models.UserTable
import org.jetbrains.exposed.sql.insertAndGetId
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

class UserRepository {

    fun getAllUsers(): List<User> = transaction {
        UserTable.selectAll().map { row ->
            return@map User(row[UserTable.id].value, row[UserTable.fullName], row[UserTable.email])
        }
    }

    fun getUserById(userId: Long): User? = transaction {
        UserTable.select { UserTable.id eq userId }
            .map { row -> User(row[UserTable.id].value, row[UserTable.fullName], row[UserTable.email]) }
            .firstOrNull()
    }

    fun saveUser(user: User): User {
        return transaction {
            val result = UserTable.insertAndGetId {
                it[fullName] = user.fullName
                it[email] = user.email
            }

            user.id = result.value
            user
        }
    }

}
