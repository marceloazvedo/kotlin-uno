package br.com.marcelo.azevedo.handler.action

import br.com.marcelo.azevedo.handler.AbstractStep
import br.com.marcelo.azevedo.mediator.Mediator
import br.com.marcelo.azevedo.mediator.MediatorEvent
import br.com.marcelo.azevedo.model.GameContext
import br.com.marcelo.azevedo.model.enums.CardType

class SpecialCardEffectStep(
    private val mediator: Mediator,
    private val gameContext: GameContext
) : AbstractStep(gameContext, mediator) {

    override fun execute() {

        val cardPlayed = gameContext.lastCardPlayed()
        gameContext.isSpecialEffectActive = true

        when (cardPlayed.cardType) {
            CardType.REVERT -> mediator.notify(MediatorEvent.REVERT_GAME_DIRECTION)
            CardType.BLOCK -> mediator.notify(MediatorEvent.NEXT_TURN)
            CardType.PLUS_TWO -> mediator.notify(MediatorEvent.NEXT_TURN, MediatorEvent.MAKE_PLAYER_GET_CARDS)
            CardType.JOKER -> {
                gameContext.isSpecialEffectActive = false
                mediator.notify(MediatorEvent.SELECT_COLOR_GAME)
            }

            CardType.JOKER_PLUS_FOUR -> mediator.notify(MediatorEvent.SELECT_COLOR_GAME)
            else -> {
                gameContext.isSpecialEffectActive = false
                mediator.notify(MediatorEvent.NEXT_TURN)
            }
        }
    }
}