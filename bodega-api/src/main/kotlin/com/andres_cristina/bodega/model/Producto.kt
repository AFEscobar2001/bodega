package com.andres_cristina.bodega.model

import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank

@Entity
@Table(
    name = "productos",
    indexes = [Index(name = "ix_prod_sku", columnList = "sku", unique = true)]
)
class Producto(
    @Id
    @Column(length = 36)
    val id: String = java.util.UUID.randomUUID().toString(),

    @field:NotBlank
    @Column(nullable = false, unique = true, length = 50)
    val sku: String,

    @field:NotBlank
    @Column(nullable = false, length = 100)
    val nombre: String,

    @field:NotBlank
    @Column(nullable = false, length = 50)
    val tipo: String,

    @field:NotBlank
    @Column(nullable = false, length = 20)
    val unidad: String
)