package org.iesra

import java.time.LocalDateTime
import org.slf4j.LoggerFactory

// Ejercicio 4.6
/**
 * Clase Compra
 * @param cliente cliente que realizo la compra
 * @param dia dia de la compra
 * @param monto monto de la compra
 * @constructor Crea una compra con cliente, dia y monto
 */
data class Compra(var cliente: Cliente, var dia: Int, var monto: Int)
{

}

/**
 * Clase Cliente
 * @param nombre nombre del cliente
 * @param domicilio domicilio del cliente
 * @constructor Crea un cliente con nombre y domicilio
 */
data class Cliente(var nombre: String, var domicilio: Domicilio)
{

}


/**
 * Clase Domicilio
 * @param calle calle del domicilio
 * @param numero numero del domicilio
 * @constructor Crea un domicilio con calle y numero
 */
data class Domicilio(var calle: String, var numero: Int)
{
    fun dirCompleta(): String
    { return "Calle: $calle, num: $numero" }
}

/**
 * Clase RepositorioCompras
 * @constructor Crea un repositorio de compras
 */
data class RepositorioCompras()
{
    fun agregarCompra()
    {

    }

    fun domicilios()
    {

    }
}


// Ejercicio 4.7
class Cuenta(val cuenta: Int, var saldo: Double)
{
    fun abono(cantidad: Double, cuenta: Cuenta)
    {
        this.saldo -= cantidad
        cuenta.saldo += cantidad
    }

    fun pago(cantidad: Double, cuenta: Cuenta)
    {
        this.saldo -= cantidad
        cuenta.saldo += cantidad
    }

    companion object {
        fun esMorosa(cuenta: Persona): Boolean
        { return cuenta.cuentas.any { (it.saldo ?: 0.0) < 0.0 } }

        fun transferencia(persona_origen: Persona, persona_destino: Persona, cuenta_origen: Int, cuenta_destino: Int, cantidad: Double): Boolean
        {
            fun encontrarCuenta(persona: Persona, cuenta: Int): Int
            { return persona.cuentas.indexOfFirst() { it.cuenta == cuenta } }

            try {
                persona_origen.cuentas[encontrarCuenta(persona_origen, cuenta_origen)]
                    .pago(cantidad, persona_destino.cuentas[encontrarCuenta(persona_destino, cuenta_destino)])
            } catch (error: Exception) { return false }

            return true
        }
    }
}

class Persona(var dni: String, var cuentas: ArrayList<Cuenta>)
{
    fun addCuenta(cuenta: Cuenta) { if (cuentas.size > 3) cuentas.add(cuenta) }
}

// Ejercicio 4.8
class Libro(var titulo: String, var autor: String, var numPags: Int, var calificacion: Int)
{
    init {
        if (calificacion !in 0..10) throw Exception("$calificacion debe de estar entre 0 y 10")
    }
}

class ConjuntoLibros(var conjuntoLibros: ArrayList<Libro>)
{
    fun addLibro(libro: Libro) { if (conjuntoLibros.size < 3) conjuntoLibros.add(libro) }

    fun eliminarAutor(libro: Libro, autor: String)
    { conjuntoLibros.remove(conjuntoLibros[conjuntoLibros.indexOfFirst() { it.autor == autor }]) }

    fun eliminarTitulo(libro: Libro, titulo: String)
    { conjuntoLibros.remove(conjuntoLibros[conjuntoLibros.indexOfFirst() { it.titulo == titulo }]) }

    override fun toString(): String { return "ConjuntoLibros($conjuntoLibros)" }
}

enum class TareaEstado { PENDIENTE, COMPLETADA }

// Ejercicio 4.9
class Tarea(var descripcion: String, var estado: TareaEstado? = TareaEstado.PENDIENTE)
{
    var id: Int = 0
    var fecha: LocalDateTime? = null

    init { id++ }
}

class conjuntoTareas(var conjuntoTareas: ArrayList<Tarea>)
{
    fun addTarea(tarea: Tarea) { conjuntoTareas.add(tarea) }

    fun cambiarEstado(id: Int, estado: TareaEstado)
    {
        val tarea: Tarea = conjuntoTareas[conjuntoTareas.indexOfFirst() { it.id == id }]
        if (estado == TareaEstado.COMPLETADA) tarea.fecha = LocalDateTime.now()
        tarea.estado = estado
    }

    fun eliminarEstado(id: Int)
    {
        val tarea: Tarea = conjuntoTareas[conjuntoTareas.indexOfFirst() { it.id == id }]
        tarea.estado = null
        tarea.fecha = null
    }
}

// Ejercicio 4.10
//class Tablero(val dimension: Int, val fichas: ArrayList<Ficha>)
//{
//    private val tablero = Array(dimension) { Array(dimension) { "O" } }
//    private var started: Boolean = false
//
//    fun startGame()
//    {
//        started = true
//        while (started) {
//            val fichasA: Array<Ficha> = [Ficha(), Ficha(), Ficha()]
//            val fichasB: Array<Ficha> = [Ficha(), Ficha(), Ficha()]
//        }
//    }
//
//    fun viewTablero()
//    {
//        for (i in 0..<tablero.size) {
//            println(tablero[i])
//        }
//    }
//}
//
//enum class Color { AZUL, ROJO }
//class Ficha(color: Color)
//{
//
//}


val logger = LoggerFactory.getLogger("Tarea")

fun main() {
    logger.trace("Mensaje TRACE")
    logger.debug("Mensaje DEBUG")
    logger.info("Mensaje INFO")
    logger.warn("Mensaje WARN")
    logger.error("Mensaje ERROR")
}