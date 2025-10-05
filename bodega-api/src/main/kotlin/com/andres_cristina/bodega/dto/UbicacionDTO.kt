package com.andres_cristina.bodega.dto

import jakarta.validation.constraints.NotBlank

data class UbicacionCreateDto(
    @field:NotBlank val codigo: String,
    val descripcion: String?
)

data class UbicacionViewDto(
    val id: String,
    val codigo: String,
    val descripcion: String?
)