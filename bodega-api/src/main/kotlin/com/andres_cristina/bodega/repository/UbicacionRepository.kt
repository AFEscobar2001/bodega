package com.andres_cristina.bodega.repository

import com.andres_cristina.bodega.model.Ubicacion
import org.springframework.data.jpa.repository.JpaRepository

interface UbicacionRepository : JpaRepository<Ubicacion, String> {
    fun findByCodigo(codigo: String): Ubicacion?
}
