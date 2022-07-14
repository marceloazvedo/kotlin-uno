package br.com.marcelo.azevedo.handler.action

import br.com.marcelo.azevedo.exceptions.InvalidCardPlayed
import br.com.marcelo.azevedo.handler.StepHandler
import br.com.marcelo.azevedo.mediator.Mediator
import br.com.marcelo.azevedo.mediator.MediatorEvent
import br.com.marcelo.azevedo.model.Card
import br.com.marcelo.azevedo.model.Game
import br.com.marcelo.azevedo.model.enums.CardType

class ValidatePlayerHasCardStepHandler(
    private val mediator: Mediator,
    private val game: Game,
) : StepHandler(game) {

    override fun execute() {
        val previousCard = game.lastCardPlayed()
        val hasNoOneCard: Boolean = game.playerInTurn.cards.none {
            val isValidCard = try {
                validateCardToPlaay(previousCard, it)
                true
            } catch (_: Exception) {
                false
            }
            isValidCard
        }
        if (hasNoOneCard) mediator.notify(this, MediatorEvent.GET_CARD)
        else mediator.notify(this, MediatorEvent.CHOSE_CARD)
    }

    private fun validateCardToPlaay(previousCard: Card, cardPlayed: Card) {
        if (cardPlayed.cardType == CardType.NUMBER && !(previousCard.color == cardPlayed.color || previousCard.value == cardPlayed.value)) {
            throw InvalidCardPlayed(cardPlayed)
        }
        if (cardPlayed.cardType == CardType.BLOCK && previousCard.color != cardPlayed.color) {
            throw InvalidCardPlayed(cardPlayed)
        }
        if (cardPlayed.cardType == CardType.REVERT && previousCard.color != cardPlayed.color) {
            throw InvalidCardPlayed(cardPlayed)
        }
        if (cardPlayed.cardType == CardType.PLUS_TWO && previousCard.color != cardPlayed.color) {
            throw InvalidCardPlayed(cardPlayed)
        }
    }

}