package org.iesra

import java.time.LocalDateTime


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
        {
            for (i in cuenta.cuentas) { if (i.saldo < 0) return true }
            return false
        }

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



fun main()
{

}