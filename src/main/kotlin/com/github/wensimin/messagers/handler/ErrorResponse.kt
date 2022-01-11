package com.github.wensimin.messagers.handler

class ErrorResponse(val error: ErrorType, val message: String)
enum class ErrorType {
    ERROR,PARAM
}
