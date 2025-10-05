package com.andres_cristina.bodega.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Positive
import java.math.BigDecimal

data class EntradaReqDto(
    @field:NotBlank val sku: String,
    @field:NotBlank val lote: String,
    @field:NotBlank val ubicacion: String,
    @field:Positive val cantidad: BigDecimal,
    val fechaVencimiento: String? = null,
    val motivo: String? = null
)

data class SalidaReqDto(
    @field:NotBlank val sku: String,
    @field:NotBlank val ubicacion: String,
    @field:Positive val cantidad: BigDecimal,
    val motivo: String? = null
)