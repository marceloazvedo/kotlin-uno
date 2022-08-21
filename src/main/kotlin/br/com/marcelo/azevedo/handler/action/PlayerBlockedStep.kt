package br.com.marcelo.azevedo.handler.action

import br.com.marcelo.azevedo.handler.AbstractStep
import br.com.marcelo.azevedo.mediator.Mediator
import br.com.marcelo.azevedo.mediator.MediatorEvent
import br.com.marcelo.azevedo.model.GameContext

class PlayerBlockedStep(
    val mediator: Mediator,
    val gameContext: GameContext
) : AbstractStep(gameContext, mediator) {

    override fun execute() {
        println("The player ${gameContext.playerInTurn.name} has blocked!")
        gameContext.isSpecialEffectActive = false
        mediator.notify(this, MediatorEvent.NEXT_TURN)
    }

}