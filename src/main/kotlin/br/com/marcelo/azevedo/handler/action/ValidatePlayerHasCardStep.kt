package br.com.marcelo.azevedo.handler.action

import br.com.marcelo.azevedo.mediator.Mediator
import br.com.marcelo.azevedo.mediator.MediatorEvent
import br.com.marcelo.azevedo.model.GameContext

class ValidatePlayerHasCardStep(
    private val mediator: Mediator,
    private val gameContext: GameContext,
) : CardValidationStep(gameContext, mediator) {

    override fun execute() {
        val previousCard = gameContext.lastCardPlayed()
        val hasNoOneCard: Boolean =
            gameContext.playerInTurn.cards.none { validateCardToPlaay(gameContext.turnColor, previousCard, it) }
        if (hasNoOneCard) {
            println("\nThe player ${gameContext.playerInTurn.name} has no cards to play!")
            mediator.notify(MediatorEvent.GET_CARD)
        } else mediator.notify(MediatorEvent.CHOSE_CARD)
    }

}