package br.com.marcelo.azevedo.handler.action

import br.com.marcelo.azevedo.exceptions.InvalidCardPlayed
import br.com.marcelo.azevedo.handler.StepHandler
import br.com.marcelo.azevedo.mediator.Mediator
import br.com.marcelo.azevedo.mediator.MediatorEvent
import br.com.marcelo.azevedo.model.GameContext
import br.com.marcelo.azevedo.model.enums.CardType

class ValidateCardPlayedStepHandler(
    private val mediator: Mediator,
    private val gameContext: GameContext,
) : StepHandler(gameContext, mediator) {

    override fun execute() {
        val previousCard = gameContext.lastCardPlayed()
        val cardSelectedToPlay = gameContext.cardSelectToPlay

        if (cardSelectedToPlay == null) {
            mediator.notify(this, MediatorEvent.CHOSE_CARD)
            return
        }

        try {
            if (cardSelectedToPlay.cardType != previousCard.cardType && cardSelectedToPlay.color != gameContext.turnColor) {
                throw InvalidCardPlayed(cardSelectedToPlay)
            }
            if (cardSelectedToPlay.cardType == CardType.NUMBER && !(gameContext.turnColor == cardSelectedToPlay.color || previousCard.value == cardSelectedToPlay.value)) {
                throw InvalidCardPlayed(cardSelectedToPlay)
            }
        } catch (_: Exception) {
            mediator.notify(this, MediatorEvent.CHOSE_CARD)
            return
        }

        gameContext.turnColor = cardSelectedToPlay.color
        gameContext.playerInTurn.cards.remove(cardSelectedToPlay)
        gameContext.playedCards.add(cardSelectedToPlay)

        if (gameContext.playerInTurn.cards.isEmpty()) {
            mediator.notify(this, MediatorEvent.END_GAME)
        } else if (cardSelectedToPlay.isSpecial()) {
            mediator.notify(this, MediatorEvent.ACTIVATE_SPECIAL_CARD_EFFECT)
        } else {
            mediator.notify(this, MediatorEvent.NEXT_TURN)
        }

    }
}