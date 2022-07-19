package br.com.marcelo.azevedo.handler.action

import br.com.marcelo.azevedo.handler.StepHandler
import br.com.marcelo.azevedo.mediator.Mediator
import br.com.marcelo.azevedo.mediator.MediatorEvent
import br.com.marcelo.azevedo.model.Game
import br.com.marcelo.azevedo.model.enums.CardType
import br.com.marcelo.azevedo.model.enums.GameDirection

class NextTurnStepHandler(val mediator: Mediator, val game: Game) : StepHandler(game, mediator) {

    override fun execute() {
        val playerIndex = game.players.indexOf(game.playerInTurn)
        val nextIndex = if (game.direction == GameDirection.FORWARD) {
            playerIndex.inc()
        } else {
            playerIndex.dec()
        }.let { index ->
            if (index < 0) game.players.size - 1
            else if (index >= game.players.size) 0
            else index
        }
        val nextPlayer = game.players[nextIndex]
        game.playerInTurn = nextPlayer
        executeNextAndNotify(MediatorEvent.UNDER_EFFECT_VERIFICATION)
    }
}