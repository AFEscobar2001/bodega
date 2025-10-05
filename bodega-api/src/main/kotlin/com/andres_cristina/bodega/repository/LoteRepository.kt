package com.andres_cristina.bodega.repository

import com.andres_cristina.bodega.model.Lote
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface LoteRepository : JpaRepository<Lote, String> {

    @Query("""
        SELECT l FROM Lote l
        WHERE l.producto.id = :pid
          AND l.ubicacion.id = :uid
          AND l.stock > 0
        ORDER BY l.fechaIngreso ASC, l.id ASC
    """)
    fun findFifo(pid: String, uid: String): List<Lote>
}
