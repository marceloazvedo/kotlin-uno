package br.com.marcelo.azevedo.handler.action

import br.com.marcelo.azevedo.handler.StepHandler
import br.com.marcelo.azevedo.mediator.Mediator
import br.com.marcelo.azevedo.mediator.MediatorEvent
import br.com.marcelo.azevedo.model.GameContext
import br.com.marcelo.azevedo.model.enums.GameDirection

class RevertGameDirectionStepHandler(private val mediator: Mediator, private val gameContext: GameContext) : StepHandler(gameContext, mediator) {

    override fun execute() {
        gameContext.direction =
            if (gameContext.direction == GameDirection.FORWARD) GameDirection.BACKWARD
            else GameDirection.FORWARD
        gameContext.isSpecialEffectActive = false
        mediator.notify(this, MediatorEvent.NEXT_TURN)
    }
}