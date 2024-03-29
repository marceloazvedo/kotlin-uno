package br.com.marcelo.azevedo.handler.action

import br.com.marcelo.azevedo.handler.AbstractStep
import br.com.marcelo.azevedo.mediator.Mediator
import br.com.marcelo.azevedo.mediator.MediatorEvent
import br.com.marcelo.azevedo.model.GameContext
import br.com.marcelo.azevedo.model.enums.GameDirection

class RevertGameDirectionStep(
    private val mediator: Mediator,
    private val gameContext: GameContext
) : AbstractStep(gameContext, mediator) {

    override fun execute() {
        gameContext.direction =
            if (gameContext.direction == GameDirection.FORWARD) GameDirection.BACKWARD
            else GameDirection.FORWARD
        gameContext.isSpecialEffectActive = false
        mediator.notify(MediatorEvent.NEXT_TURN)
    }
}