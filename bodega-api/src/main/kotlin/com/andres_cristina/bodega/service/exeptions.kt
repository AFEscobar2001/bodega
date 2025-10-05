package com.andres_cristina.bodega.service

class EntidadNoExiste(msg: String): RuntimeException(msg)
class StockInsuficiente(msg: String): RuntimeException(msg)
class ReglaNegocio(msg: String): RuntimeException(msg)
