package br.com.marcelo.azevedo.handler.action

import br.com.marcelo.azevedo.handler.StepHandler
import br.com.marcelo.azevedo.mediator.Mediator
import br.com.marcelo.azevedo.mediator.MediatorEvent
import br.com.marcelo.azevedo.model.Game
import br.com.marcelo.azevedo.model.enums.CardType

class SpecialCardEffectStepHandler(private val mediator: Mediator, private val game: Game) : StepHandler(game) {

    override fun execute() {

        val cardPlayed = game.lastCardPlayed()
        game.isSpecialEffectActive = true

        when (cardPlayed.cardType) {
            CardType.REVERT -> mediator.notify(this, MediatorEvent.REVERT_GAME_DIRECTION)
            CardType.BLOCK -> mediator.notify(this, MediatorEvent.PLAYER_BLOCKED)
            CardType.PLUS_TWO -> mediator.notify(this, MediatorEvent.MAKE_PLAYER_GET_CARDS)
            CardType.JOKER -> mediator.notify(this, MediatorEvent.SELECT_COLOR_GAME)
            CardType.JOKER_PLUS_FOUR -> mediator.notify(this, MediatorEvent.SELECT_COLOR_GAME)
            else -> mediator.notify(this, MediatorEvent.NEXT_TURN)
        }
    }
}