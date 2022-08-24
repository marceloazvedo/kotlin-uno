package br.com.marcelo.azevedo.handler.input

import br.com.marcelo.azevedo.ui.CardUI
import br.com.marcelo.azevedo.handler.AbstractStep
import br.com.marcelo.azevedo.mediator.Mediator
import br.com.marcelo.azevedo.mediator.MediatorEvent
import br.com.marcelo.azevedo.model.GameContext
import br.com.marcelo.azevedo.model.enums.CardColor
import br.com.marcelo.azevedo.model.enums.CardType

class SelectColorStepHandler(
    private val mediator: Mediator,
    private val gameContext: GameContext,
): AbstractStep(gameContext, mediator) {

    private val cardUI = CardUI()

    override fun execute() {
        var cardColor: CardColor? = null
        while (cardColor == null) {
            try {
                println(cardUI.printCards(gameContext.playerInTurn.cards))
                print("Please, selec a valid color ${gameContext.playerInTurn.name} (red, blue, green or yellow): ")
                val cardColorSelected = readln()
                cardColor = CardColor.values().map {
                    if (it != CardColor.NO_COLOR && it.colorName == cardColorSelected) it
                    else null
                }.firstOrNull { it != null }
            } catch (_: Exception) {
                println(
                    """
                        
                        
                        
                        
                        
                        
                        ## WARNING ##
                        Invalid color!
                        #############
                """.trimIndent()
                )
            }
        }
        gameContext.turnColor = cardColor
        if (gameContext.lastCardPlayed().cardType == CardType.JOKER_PLUS_FOUR) {
            gameContext.isSpecialEffectActive = true
            mediator.notify(MediatorEvent.NEXT_TURN, MediatorEvent.MAKE_PLAYER_GET_CARDS)
        }
        else {
            gameContext.isSpecialEffectActive = false
            mediator.notify(MediatorEvent.NEXT_TURN)
        }
    }
}