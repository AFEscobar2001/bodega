package com.andres_cristina.bodega.model

import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank

@Entity
@Table(
    name = "ubicaciones",
    indexes = [Index(name = "ix_ubic_codigo", columnList = "codigo", unique = true)]
)
class Ubicacion(
    @Id
    @Column(length = 36)
    val id: String = java.util.UUID.randomUUID().toString(),

    @field:NotBlank
    @Column(nullable = false, unique = true, length = 50)
    val codigo: String,

    @Column(length = 255)
    val descripcion: String? = null
)