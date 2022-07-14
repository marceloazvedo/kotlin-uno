package br.com.marcelo.azevedo.handler.action

import br.com.marcelo.azevedo.handler.StepHandler
import br.com.marcelo.azevedo.mediator.Mediator
import br.com.marcelo.azevedo.mediator.MediatorEvent
import br.com.marcelo.azevedo.model.Game
import br.com.marcelo.azevedo.model.enums.GameDirection

class RevertGameDirectionStepHandler(private val mediator: Mediator, private val game: Game) : StepHandler(game) {

    override fun execute() {
        game.direction =
            if (game.direction == GameDirection.FORWARD) GameDirection.BACKWARD
            else GameDirection.FORWARD
        game.isSpecialEffectActive = false
        mediator.notify(this, MediatorEvent.NEXT_TURN)
    }
}