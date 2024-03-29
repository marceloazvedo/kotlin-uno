package br.com.marcelo.azevedo.handler.action

import br.com.marcelo.azevedo.handler.AbstractStep
import br.com.marcelo.azevedo.mediator.Mediator
import br.com.marcelo.azevedo.mediator.MediatorEvent
import br.com.marcelo.azevedo.model.GameContext
import br.com.marcelo.azevedo.model.enums.CardType

class GetCardStep(
    private val mediator: Mediator,
    private val gameContext: GameContext
) : AbstractStep(gameContext, mediator) {

    override fun execute() {
        val cardPlayed = gameContext.lastCardPlayed()
        val quantityOfCardsToGet =
            if (cardPlayed.isSpecial() || gameContext.isSpecialEffectActive)
                when (cardPlayed.cardType) {
                    CardType.JOKER_PLUS_FOUR -> 4
                    CardType.PLUS_TWO -> 2
                    else -> 1
                }
            else 1
        val cards = gameContext.remainingCards.take(quantityOfCardsToGet)
        gameContext.remainingCards = gameContext.remainingCards.drop(quantityOfCardsToGet).toMutableList()
        gameContext.playerInTurn.cards += cards
        gameContext.isSpecialEffectActive = false
        mediator.notify(MediatorEvent.NEXT_TURN)
    }

}