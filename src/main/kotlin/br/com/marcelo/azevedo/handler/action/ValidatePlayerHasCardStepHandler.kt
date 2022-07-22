package br.com.marcelo.azevedo.handler.action

import br.com.marcelo.azevedo.handler.StepHandler
import br.com.marcelo.azevedo.mediator.Mediator
import br.com.marcelo.azevedo.mediator.MediatorEvent
import br.com.marcelo.azevedo.model.Card
import br.com.marcelo.azevedo.model.GameContext
import br.com.marcelo.azevedo.model.enums.CardType

class ValidatePlayerHasCardStepHandler(
    private val mediator: Mediator,
    private val gameContext: GameContext,
) : StepHandlerCardValidator(gameContext, mediator) {

    override fun execute() {
        val previousCard = gameContext.lastCardPlayed()
        val hasNoOneCard: Boolean =
            gameContext.playerInTurn.cards.none { validateCardToPlaay(gameContext.turnColor, previousCard, it) }
        if (hasNoOneCard) {
            println("\nThe player ${gameContext.playerInTurn.name} has no cards to play!")
            mediator.notify(this, MediatorEvent.GET_CARD)
        } else mediator.notify(this, MediatorEvent.CHOSE_CARD)
    }

}