package br.com.marcelo.azevedo.handler.action

import br.com.marcelo.azevedo.handler.AbstractStep
import br.com.marcelo.azevedo.mediator.Mediator
import br.com.marcelo.azevedo.model.GameContext

class EndGameStep(
    private val mediator: Mediator,
    private val gameContext: GameContext,
): AbstractStep(gameContext, mediator) {

    override fun execute() {
        println("We have a winner! The winner is ${gameContext.playerInTurn.name}")
    }
}