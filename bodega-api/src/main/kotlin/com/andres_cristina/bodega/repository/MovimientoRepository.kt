package com.andres_cristina.bodega.repository

import com.andres_cristina.bodega.model.Movimiento
import org.springframework.data.jpa.repository.JpaRepository

interface MovimientoRepository : JpaRepository<Movimiento, String>
