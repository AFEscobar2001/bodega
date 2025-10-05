package com.andres_cristina.bodega.dto

import jakarta.validation.constraints.NotBlank

data class ProductoCreateDto(
    @field:NotBlank val sku: String,
    @field:NotBlank val nombre: String,
    @field:NotBlank val tipo: String,
    @field:NotBlank val unidad: String
)

data class ProductoViewDto(
    val id: String,
    val sku: String,
    val nombre: String,
    val tipo: String,
    val unidad: String
)