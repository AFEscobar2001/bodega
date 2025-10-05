package com.andres_cristina.bodega.model

import jakarta.persistence.*
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank

@Entity
@Table(name = "usuarios")
class Usuario(
    @Id
    @Column(length = 36)
    val id: String = java.util.UUID.randomUUID().toString(),

    @field:Email
    @field:NotBlank
    @Column(unique = true, nullable = false, length = 100)
    val email: String,

    @field:NotBlank
    @Column(nullable = false, length = 255)
    val hash: String,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    val rol: Rol = Rol.operador,

    @Column(nullable = false)
    val activo: Boolean = true
)