package com.andres_cristina.bodega.controller

import com.andres_cristina.bodega.service.ProductoService
import org.springframework.web.bind.annotation.*
import com.andres_cristina.bodega.dto.*
import jakarta.validation.Valid


@RestController
@RequestMapping("/productos")
class ProductoController(private val service: ProductoService) {

    @PostMapping
    fun crear(@Valid @RequestBody body: ProductoCreateDto): ProductoViewDto =
        service.crear(body.sku, body.nombre, body.tipo, body.unidad).let {
            ProductoViewDto(it.id, it.sku, it.nombre, it.tipo, it.unidad)
        }

    @GetMapping("/{sku}")
    fun buscarPorSku(@PathVariable sku: String): ProductoViewDto =
        service.porSku(sku).let { ProductoViewDto(it.id, it.sku, it.nombre, it.tipo, it.unidad) }
}