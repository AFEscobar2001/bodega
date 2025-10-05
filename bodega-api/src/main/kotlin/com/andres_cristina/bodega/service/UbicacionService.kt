package com.andres_cristina.bodega.service

import com.andres_cristina.bodega.model.Ubicacion
import com.andres_cristina.bodega.repository.UbicacionRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UbicacionService(private val repo: UbicacionRepository) {

    @Transactional
    fun crear(codigo: String, descripcion: String?): Ubicacion {
        if (repo.findByCodigo(codigo) != null) throw ReglaNegocio("Ubicación ya existe: $codigo")
        return repo.save(Ubicacion(codigo = codigo, descripcion = descripcion))
    }

    fun porCodigo(codigo: String): Ubicacion =
        repo.findByCodigo(codigo) ?: throw EntidadNoExiste("Ubicación no existe: $codigo")
}
