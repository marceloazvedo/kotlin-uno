package br.com.marcelo.azevedo.handler.action

import br.com.marcelo.azevedo.handler.StepHandler
import br.com.marcelo.azevedo.mediator.Mediator
import br.com.marcelo.azevedo.mediator.MediatorEvent
import br.com.marcelo.azevedo.model.GameContext

class PlayerBlockedStepHandler(val mediator: Mediator, val gameContext: GameContext) : StepHandler(gameContext, mediator) {

    override fun execute() {
        println("The player ${gameContext.playerInTurn.name} has blocked!")
        gameContext.isSpecialEffectActive = false
        mediator.notify(this, MediatorEvent.NEXT_TURN)
    }

}