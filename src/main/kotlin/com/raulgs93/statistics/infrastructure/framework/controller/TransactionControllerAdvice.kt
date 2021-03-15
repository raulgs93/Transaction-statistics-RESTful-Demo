package com.raulgs93.statistics.infrastructure.framework.controller

import com.raulgs93.statistics.domain.transaction.InvalidJSONException
import com.raulgs93.statistics.domain.transaction.TransactionFormatException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler


@ControllerAdvice
class TransactionControllerAdvice {

    @ExceptionHandler(InvalidJSONException::class)
    fun handleInvalidJSONException(exception: Exception): ResponseEntity<Any> {
        val message = exception.message
        return ResponseEntity(message, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(TransactionFormatException::class)
    fun handleTransactionFormatException(exception: Exception): ResponseEntity<Any> {
        val message = exception.message
        return ResponseEntity(message, HttpStatus.UNPROCESSABLE_ENTITY)
    }

}