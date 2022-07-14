package br.com.marcelo.azevedo.handler

import br.com.marcelo.azevedo.model.Game

abstract class StepHandler(
    private val game: Game,
) : HandlerStrategy {

    abstract override fun execute()
    override fun getHandlerGame(): Game = this.game

}