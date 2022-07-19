package br.com.marcelo.azevedo.handler.action

import br.com.marcelo.azevedo.handler.StepHandler
import br.com.marcelo.azevedo.mediator.Mediator
import br.com.marcelo.azevedo.mediator.MediatorEvent
import br.com.marcelo.azevedo.model.Game

class PlayerBlockedStepHandler(val mediator: Mediator, val game: Game) : StepHandler(game, mediator) {

    override fun execute() {
        println("The player ${game.playerInTurn.name} has blocked!")
        game.isSpecialEffectActive = false
        mediator.notify(this, MediatorEvent.NEXT_TURN)
    }

}