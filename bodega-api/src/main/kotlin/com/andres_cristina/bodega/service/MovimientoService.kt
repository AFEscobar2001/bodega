package com.andres_cristina.bodega.service


import com.andres_cristina.bodega.model.*
import com.andres_cristina.bodega.repository.*
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime

@Service
class MovimientoService(
    private val prodRepo: ProductoRepository,
    private val ubicRepo: UbicacionRepository,
    private val loteRepo: LoteRepository,
    private val movRepo: MovimientoRepository
) {
    @Transactional
    fun entrada(
        sku: String,
        loteCodigo: String,
        ubicacionCodigo: String,
        cantidad: BigDecimal,
        fechaIngreso: LocalDateTime = LocalDateTime.now(),
        fechaVencimiento: LocalDate? = null,
        motivo: String? = null
    ): String {
        require(cantidad > BigDecimal.ZERO) { "Cantidad debe ser > 0" }
        val prod = prodRepo.findBySku(sku) ?: throw EntidadNoExiste("Producto no existe: $sku")
        val ubic = ubicRepo.findByCodigo(ubicacionCodigo) ?: throw EntidadNoExiste("Ubicación no existe: $ubicacionCodigo")

        val lote = Lote(
            producto = prod,
            codigo = loteCodigo,
            fechaIngreso = fechaIngreso,
            fechaVencimiento = fechaVencimiento,
            ubicacion = ubic,
            stock = cantidad
        )
        val saved = loteRepo.save(lote)

        movRepo.save(
            Movimiento(
                tipo = TipoMovimiento.ENTRADA,
                producto = prod,
                lote = saved,
                origen = null,
                destino = ubic,
                cantidad = cantidad,
                motivo = motivo
            )
        )
        return saved.id
    }

    @Transactional
    fun salida(
        sku: String,
        ubicacionCodigo: String,
        cantidad: BigDecimal,
        motivo: String? = null
    ) {
        require(cantidad > BigDecimal.ZERO) { "Cantidad debe ser > 0" }
        val prod = prodRepo.findBySku(sku) ?: throw EntidadNoExiste("Producto no existe: $sku")
        val ubic = ubicRepo.findByCodigo(ubicacionCodigo) ?: throw EntidadNoExiste("Ubicación no existe: $ubicacionCodigo")

        var restante = cantidad
        val lotes = loteRepo.findFifo(prod.id, ubic.id)

        for (l in lotes) {
            if (restante <= BigDecimal.ZERO) break
            val tomar = if (l.stock < restante) l.stock else restante
            if (tomar > BigDecimal.ZERO) {
                l.stock = l.stock - tomar
                movRepo.save(
                    Movimiento(
                        tipo = TipoMovimiento.SALIDA,
                        producto = prod,
                        lote = l,
                        origen = ubic,
                        destino = null,
                        cantidad = tomar,
                        motivo = motivo
                    )
                )
                restante -= tomar
            }
        }
        if (restante > BigDecimal.ZERO) {
            throw StockInsuficiente("Stock insuficiente. Falta $restante de $sku en $ubicacionCodigo")
        }
    }

    fun stockPorSkuUbicacion(sku: String, ubicacionCodigo: String): BigDecimal {
        val prod = prodRepo.findBySku(sku) ?: throw EntidadNoExiste("Producto no existe: $sku")
        val ubic = ubicRepo.findByCodigo(ubicacionCodigo) ?: throw EntidadNoExiste("Ubicación no existe: $ubicacionCodigo")
        return loteRepo.findFifo(prod.id, ubic.id).fold(BigDecimal.ZERO) { acc, l -> acc + l.stock }
    }
}
