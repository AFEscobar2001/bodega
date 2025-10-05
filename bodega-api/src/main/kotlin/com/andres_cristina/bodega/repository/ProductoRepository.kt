package com.andres_cristina.bodega.repository

import com.andres_cristina.bodega.model.Producto
import org.springframework.data.jpa.repository.JpaRepository

interface ProductoRepository : JpaRepository<Producto, String> {
    fun findBySku(sku: String): Producto?
}
