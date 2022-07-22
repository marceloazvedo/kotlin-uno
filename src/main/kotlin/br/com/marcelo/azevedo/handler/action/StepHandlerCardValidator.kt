package br.com.marcelo.azevedo.handler.action

import br.com.marcelo.azevedo.handler.StepHandler
import br.com.marcelo.azevedo.mediator.Mediator
import br.com.marcelo.azevedo.model.Card
import br.com.marcelo.azevedo.model.GameContext
import br.com.marcelo.azevedo.model.enums.CardColor
import br.com.marcelo.azevedo.model.enums.CardType

abstract class StepHandlerCardValidator(
   private val gameContext: GameContext,
   private val mediator: Mediator
): StepHandler(gameContext, mediator) {

    fun validateCardToPlaay(turnColor: CardColor, previousCard: Card, possibleCardToPlay: Card): Boolean {
        if (possibleCardToPlay.cardType != CardType.JOKER && possibleCardToPlay.cardType != CardType.JOKER_PLUS_FOUR) {
            if (possibleCardToPlay.cardType == CardType.NUMBER && !(possibleCardToPlay.value == previousCard.value || possibleCardToPlay.color == turnColor))
                return false
            if (possibleCardToPlay.cardType != previousCard.cardType && possibleCardToPlay.color != turnColor)
                return false
        }
        return true
    }

}