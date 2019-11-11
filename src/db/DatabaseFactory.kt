package com.example.db

import com.example.models.UserTable
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils.create
import org.jetbrains.exposed.sql.transactions.transaction

object DatabaseFactory {

    fun init() {
        connectDb()
    }

    private fun hikari(): HikariDataSource {
        return HikariDataSource(HikariConfig().apply {
            this.driverClassName = "org.h2.Driver"
            this.jdbcUrl = "jdbc:h2:mem:test"
            this.maximumPoolSize = 3
            this.isAutoCommit = false
            this.transactionIsolation = "TRANSACTION_REPEATABLE_READ"
            this.username = "user"
            this.password = "password"
            this.validate()
        })
    }

    private fun connectDb() {
        Database.connect(hikari())
        transaction {
            create(UserTable)
        }
    }

    suspend fun <T> dbQuery(block: () -> T): T =
        withContext(Dispatchers.IO) {
            transaction { block() }
        }

}

