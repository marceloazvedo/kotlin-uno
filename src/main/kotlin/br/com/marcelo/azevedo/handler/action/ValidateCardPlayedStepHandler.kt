package br.com.marcelo.azevedo.handler.action

import br.com.marcelo.azevedo.exceptions.InvalidCardPlayed
import br.com.marcelo.azevedo.handler.StepHandler
import br.com.marcelo.azevedo.mediator.Mediator
import br.com.marcelo.azevedo.mediator.MediatorEvent
import br.com.marcelo.azevedo.model.Game
import br.com.marcelo.azevedo.model.enums.CardType

class ValidateCardPlayedStepHandler(
    private val mediator: Mediator,
    private val game: Game,
) : StepHandler(game, mediator) {

    override fun execute() {
        val previousCard = game.lastCardPlayed()
        val cardSelectedToPlay = game.cardSelectToPlay

        if (cardSelectedToPlay == null) {
            mediator.notify(this, MediatorEvent.CHOSE_CARD)
            return
        }

        try {
            if (cardSelectedToPlay.cardType == CardType.NUMBER && !(game.turnColor == cardSelectedToPlay.color || previousCard.value == cardSelectedToPlay.value)) {
                throw InvalidCardPlayed(cardSelectedToPlay)
            }
            if (cardSelectedToPlay.cardType == CardType.BLOCK && game.turnColor != cardSelectedToPlay.color) {
                throw InvalidCardPlayed(cardSelectedToPlay)
            }
            if (cardSelectedToPlay.cardType == CardType.REVERT && game.turnColor != cardSelectedToPlay.color) {
                throw InvalidCardPlayed(cardSelectedToPlay)
            }
            if (cardSelectedToPlay.cardType == CardType.PLUS_TWO && game.turnColor != cardSelectedToPlay.color) {
                throw InvalidCardPlayed(cardSelectedToPlay)
            }
        } catch (_: Exception) {
            mediator.notify(this, MediatorEvent.CHOSE_CARD)
        }

        game.turnColor = cardSelectedToPlay.color
        game.playerInTurn.cards.remove(cardSelectedToPlay)
        game.playedCards.add(cardSelectedToPlay)

        if (game.playerInTurn.cards.isEmpty()) {
            mediator.notify(this, MediatorEvent.END_GAME)
        } else if (cardSelectedToPlay.isSpecial()) {
            mediator.notify(this, MediatorEvent.ACTIVATE_SPECIAL_CARD_EFFECT)
        } else {
            mediator.notify(this, MediatorEvent.NEXT_TURN)
        }

    }
}