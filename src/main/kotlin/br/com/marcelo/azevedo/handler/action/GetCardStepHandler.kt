package br.com.marcelo.azevedo.handler.action

import br.com.marcelo.azevedo.handler.StepHandler
import br.com.marcelo.azevedo.mediator.Mediator
import br.com.marcelo.azevedo.mediator.MediatorEvent
import br.com.marcelo.azevedo.model.Game
import br.com.marcelo.azevedo.model.enums.CardType

class GetCardStepHandler(private val mediator: Mediator, private val game: Game) : StepHandler(game) {

    override fun execute() {
        val cardPlayed = game.lastCardPlayed()
        val quantityOfCardsToGet = when (cardPlayed.cardType) {
            CardType.JOKER_PLUS_FOUR -> 4
            CardType.PLUS_TWO -> 2
            else -> 1
        }
        val cards = game.remainingCards.take(quantityOfCardsToGet)
        game.remainingCards = game.remainingCards.drop(quantityOfCardsToGet).toMutableList()
        game.playerInTurn.cards += cards
        game.isSpecialEffectActive = false
        mediator.notify(this, MediatorEvent.NEXT_TURN)
    }

}