package br.com.marcelo.azevedo.handler.action

import br.com.marcelo.azevedo.handler.AbstractStep
import br.com.marcelo.azevedo.mediator.Mediator
import br.com.marcelo.azevedo.mediator.MediatorEvent
import br.com.marcelo.azevedo.model.GameContext
import br.com.marcelo.azevedo.model.enums.CardType

class UnderEffectVerificationStep(
    private val mediator: Mediator,
    private val gameContext: GameContext,
) : AbstractStep(gameContext, mediator) {

    override fun execute() {
        val lastCardPlayed = gameContext.lastCardPlayed()
        if (!lastCardPlayed.isSpecial()) {
            mediator.notify(MediatorEvent.VALIDATE_IF_PLAYER_HAS_CARDS)
        }
        if (gameContext.isSpecialEffectActive) {
            when (lastCardPlayed.cardType) {
                CardType.BLOCK -> mediator.notify(MediatorEvent.PLAYER_BLOCKED)
                CardType.PLUS_TWO -> mediator.notify(MediatorEvent.MAKE_PLAYER_GET_CARDS)
                CardType.JOKER_PLUS_FOUR -> mediator.notify(MediatorEvent.MAKE_PLAYER_GET_CARDS)
                else -> mediator.notify(MediatorEvent.VALIDATE_IF_PLAYER_HAS_CARDS)
            }
        } else {
            mediator.notify(MediatorEvent.VALIDATE_IF_PLAYER_HAS_CARDS)
        }

    }

}