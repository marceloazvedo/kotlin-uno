package br.com.marcelo.azevedo

import br.com.marcelo.azevedo.gui.Game

class KotlinUnoApplication

fun main(args: Array<String>) {
    println(Game().getPlayers().map { player -> player.name })
}
