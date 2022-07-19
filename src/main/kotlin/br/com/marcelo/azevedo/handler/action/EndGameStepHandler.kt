package br.com.marcelo.azevedo.handler.action

import br.com.marcelo.azevedo.handler.StepHandler
import br.com.marcelo.azevedo.mediator.Mediator
import br.com.marcelo.azevedo.model.Game

class EndGameStepHandler(
    private val mediator: Mediator,
    private val game: Game,
): StepHandler(game, mediator) {

    override fun execute() {
        println("We have a winner! The winner is ${game.playerInTurn.name}")
    }
}