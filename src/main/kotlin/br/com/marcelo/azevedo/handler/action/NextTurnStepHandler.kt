package br.com.marcelo.azevedo.handler.action

import br.com.marcelo.azevedo.handler.StepHandler
import br.com.marcelo.azevedo.mediator.Mediator
import br.com.marcelo.azevedo.mediator.MediatorEvent
import br.com.marcelo.azevedo.model.GameContext
import br.com.marcelo.azevedo.model.enums.GameDirection

class NextTurnStepHandler(val mediator: Mediator, val gameContext: GameContext) : StepHandler(gameContext, mediator) {

    override fun execute() {
        val playerIndex = gameContext.players.indexOf(gameContext.playerInTurn)
        val nextIndex = if (gameContext.direction == GameDirection.FORWARD) {
            playerIndex.inc()
        } else {
            playerIndex.dec()
        }.let { index ->
            if (index < 0) gameContext.players.size - 1
            else if (index >= gameContext.players.size) 0
            else index
        }
        val nextPlayer = gameContext.players[nextIndex]
        gameContext.playerInTurn = nextPlayer
        executeNextAndNotify(MediatorEvent.UNDER_EFFECT_VERIFICATION)
    }
}