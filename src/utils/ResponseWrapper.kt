package com.example.utils

data class ResponseWrapper<T>(val code: Int = 200, val message: String = "Successful request", val data: T)
