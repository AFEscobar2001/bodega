package com.andres_cristina.bodega.controller

import com.andres_cristina.bodega.service.MovimientoService
import org.springframework.web.bind.annotation.*
import java.math.BigDecimal
import java.time.LocalDate
import com.andres_cristina.bodega.dto.*
import jakarta.validation.Valid

data class EntradaReq(
    val sku: String,
    val lote: String,
    val ubicacion: String,
    val cantidad: BigDecimal,
    val fechaVencimiento: String? = null,
    val motivo: String? = null
)

data class SalidaReq(
    val sku: String,
    val ubicacion: String,
    val cantidad: BigDecimal,
    val motivo: String? = null
)

@RestController
@RequestMapping("/mov")
class MovimientoController(private val service: MovimientoService) {

    @PostMapping("/entrada")
    fun registrarEntrada(@Valid @RequestBody req: EntradaReqDto): String =
        service.entrada(
            sku = req.sku,
            loteCodigo = req.lote,
            ubicacionCodigo = req.ubicacion,
            cantidad = req.cantidad,
            fechaVencimiento = req.fechaVencimiento?.let(LocalDate::parse),
            motivo = req.motivo
        )

    @PostMapping("/salida")
    fun registrarSalida(@Valid @RequestBody req: SalidaReqDto) {
        service.salida(req.sku, req.ubicacion, req.cantidad, req.motivo)
    }

    @GetMapping("/stock")
    fun stockPorSkuUbicacion(@RequestParam sku: String, @RequestParam ubicacion: String): BigDecimal =
        service.stockPorSkuUbicacion(sku, ubicacion)
}