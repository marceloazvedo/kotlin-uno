package br.com.marcelo.azevedo.handler.action

import br.com.marcelo.azevedo.exceptions.InvalidCardPlayed
import br.com.marcelo.azevedo.handler.StepHandler
import br.com.marcelo.azevedo.mediator.Mediator
import br.com.marcelo.azevedo.mediator.MediatorEvent
import br.com.marcelo.azevedo.model.Card
import br.com.marcelo.azevedo.model.Game
import br.com.marcelo.azevedo.model.enums.CardColor
import br.com.marcelo.azevedo.model.enums.CardType

class ValidatePlayerHasCardStepHandler(
    private val mediator: Mediator,
    private val game: Game,
) : StepHandler(game, mediator) {

    override fun execute() {
        val previousCard = game.lastCardPlayed()
        val hasNoOneCard: Boolean =
            game.playerInTurn.cards.none { validateCardToPlaay(game.turnColor, previousCard, it) }
        if (hasNoOneCard) {
            println("\nThe player ${game.playerInTurn.name} has no cards to play!\n")
            mediator.notify(this, MediatorEvent.GET_CARD)
        } else mediator.notify(this, MediatorEvent.CHOSE_CARD)
    }

    private fun validateCardToPlaay(turnColor: CardColor, previousCard: Card, cardPlayed: Card): Boolean {
        if (cardPlayed.cardType == CardType.NUMBER && !(turnColor == cardPlayed.color || previousCard.value == cardPlayed.value)) {
            return false
        }
        if (cardPlayed.cardType == CardType.BLOCK && turnColor != cardPlayed.color) {
            return false
        }
        if (cardPlayed.cardType == CardType.REVERT && turnColor != cardPlayed.color) {
            return false
        }
        if (cardPlayed.cardType == CardType.PLUS_TWO && turnColor != cardPlayed.color) {
            return false
        }
        return true
    }

}