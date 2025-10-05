package com.andres_cristina.bodega.model

import jakarta.persistence.*
import jakarta.validation.constraints.Positive
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime

@Entity
@Table(
    name = "lotes",
    uniqueConstraints = [UniqueConstraint(name = "uk_lote_prod_codigo", columnNames = ["producto_id","codigo"])],
    indexes = [
        Index(name = "ix_lote_fifo", columnList = "producto_id,ubicacion_id,fecha_ingreso"),
        Index(name = "ix_lote_fefo", columnList = "producto_id,ubicacion_id,fecha_vencimiento")
    ]
)
class Lote(
    @Id
    @Column(length = 36)
    val id: String = java.util.UUID.randomUUID().toString(),

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "producto_id", nullable = false)
    val producto: Producto,

    @Column(nullable = false, length = 50)
    val codigo: String,

    @Column(name = "fecha_ingreso", nullable = false)
    val fechaIngreso: LocalDateTime,

    @Column(name = "fecha_vencimiento")
    val fechaVencimiento: LocalDate? = null,

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ubicacion_id", nullable = false)
    val ubicacion: Ubicacion,

    @field:Positive
    @Column(nullable = false, precision = 18, scale = 3)
    var stock: BigDecimal
)