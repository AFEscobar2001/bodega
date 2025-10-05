package com.andres_cristina.bodega.controller

import com.andres_cristina.bodega.service.UbicacionService
import org.springframework.web.bind.annotation.*
import com.andres_cristina.bodega.dto.*
import jakarta.validation.Valid


@RestController
@RequestMapping("/ubicaciones")
class UbicacionController(private val service: UbicacionService) {

    @PostMapping
    fun crear(@Valid @RequestBody body: UbicacionCreateDto): UbicacionViewDto =
        service.crear(body.codigo, body.descripcion).let {
            UbicacionViewDto(it.id, it.codigo, it.descripcion)
        }

    @GetMapping("/{codigo}")
    fun buscarPorCodigo(@PathVariable codigo: String): UbicacionViewDto =
        service.porCodigo(codigo).let { UbicacionViewDto(it.id, it.codigo, it.descripcion) }
}