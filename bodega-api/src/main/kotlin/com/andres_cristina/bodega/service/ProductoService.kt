package com.andres_cristina.bodega.service

import com.andres_cristina.bodega.model.Producto
import com.andres_cristina.bodega.repository.ProductoRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ProductoService(private val repo: ProductoRepository) {

    @Transactional
    fun crear(sku: String, nombre: String, tipo: String, unidad: String): Producto {
        if (repo.findBySku(sku) != null) throw ReglaNegocio("SKU ya existe: $sku")
        return repo.save(Producto(sku = sku, nombre = nombre, tipo = tipo, unidad = unidad))
    }

    fun porSku(sku: String): Producto =
        repo.findBySku(sku) ?: throw EntidadNoExiste("Producto no existe: $sku")
}
