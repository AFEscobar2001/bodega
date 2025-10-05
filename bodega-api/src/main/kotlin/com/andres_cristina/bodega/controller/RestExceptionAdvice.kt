package com.andres_cristina.bodega.controller

import org.springframework.http.HttpStatus
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.*
import com.andres_cristina.bodega.service.*

@RestControllerAdvice
class RestExceptionAdvice {
    @ExceptionHandler(MethodArgumentNotValidException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun onValidation(e: MethodArgumentNotValidException) =
        e.bindingResult.fieldErrors.associate { it.field to (it.defaultMessage ?: "inválido") }

    @ExceptionHandler(EntidadNoExiste::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun notFound(e: EntidadNoExiste) = mapOf("error" to e.message)

    @ExceptionHandler(ReglaNegocio::class)
    @ResponseStatus(HttpStatus.CONFLICT)
    fun conflict(e: ReglaNegocio) = mapOf("error" to e.message)

    @ExceptionHandler(StockInsuficiente::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun stock(e: StockInsuficiente) = mapOf("error" to e.message)
}