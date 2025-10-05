package com.andres_cristina.bodega.model

import jakarta.persistence.*
import jakarta.validation.constraints.Positive
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity
@Table(name = "movimientos")
class Movimiento(
    @Id
    @Column(length = 36)
    val id: String = java.util.UUID.randomUUID().toString(),

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    val tipo: TipoMovimiento, // ENTRADA|SALIDA|AJUSTE

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "producto_id", nullable = false)
    val producto: Producto,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lote_id")
    val lote: Lote? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "origen_id")
    val origen: Ubicacion? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "destino_id")
    val destino: Ubicacion? = null,

    @field:Positive
    @Column(nullable = false, precision = 18, scale = 3)
    val cantidad: BigDecimal,

    @Column(length = 255)
    val motivo: String? = null,

    @Column(nullable = false)
    val ts: LocalDateTime = LocalDateTime.now()
)