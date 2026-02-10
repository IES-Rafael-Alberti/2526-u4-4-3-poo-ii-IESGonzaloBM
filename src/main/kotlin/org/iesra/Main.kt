package org.iesra

import kotlin.random.Random

enum class RADIO { PEQUENO, AMPLIO }

open class ArmaDeFuego(protected var nombre: String,
                       protected var municion: Int,
                       protected var municionARestar: Int,
                       protected var tipoDeMunicion: String,
                       protected var danio: Int,
                       protected var radio: RADIO)
{
    fun estado(): String { return "$nombre(municion: $municion, municionARestar: $municionARestar, tipoDeMunicion: $tipoDeMunicion, danio: $danio, radio: $radio)" }

    open fun disparar() { municion -= municionARestar }

    open fun recargar() { municion++ }
}


class Pistola(nombre: String, municion: Int, municionARestar: Int, tipoDeMunicion: String, danio: Int, radio: RADIO)
    : ArmaDeFuego(nombre, municion, municionARestar, tipoDeMunicion, danio, radio)


class Rifle(nombre: String, municion: Int, municionARestar: Int, tipoDeMunicion: String, danio: Int, radio: RADIO)
    : ArmaDeFuego(nombre, municion, municionARestar, tipoDeMunicion, danio, radio)
{
    override fun disparar() { municion -= municionARestar * 2}

}


class Bazooka(nombre: String, municion: Int, municionARestar: Int, tipoDeMunicion: String, danio: Int, radio: RADIO)
    : ArmaDeFuego(nombre, municion, municionARestar, tipoDeMunicion, danio, radio)
{
    override fun disparar() { municion -= municionARestar * 3}
}



fun main()
{
    val pistola = Pistola("MiPistola", 100, 3, "Balas", 5, RADIO.PEQUENO)
    val rifle = Rifle("MiRifle", 25, 2, "BalasGrandes", 10, RADIO.AMPLIO)
    val bazooka = Bazooka("MiBazooka", 5, 1, "Misil", 30, RADIO.AMPLIO)

    val armas = mapOf<Int, ArmaDeFuego>(1 to pistola, 2 to rifle, 3 to bazooka)

    armas.forEach { K, V ->
        repeat(6) { V.disparar() }
        V.estado()
    }
}