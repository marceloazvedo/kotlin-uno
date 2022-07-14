package br.com.marcelo.azevedo.handler.input

import br.com.marcelo.azevedo.ui.CardUI
import br.com.marcelo.azevedo.handler.StepHandler
import br.com.marcelo.azevedo.mediator.Mediator
import br.com.marcelo.azevedo.mediator.MediatorEvent
import br.com.marcelo.azevedo.model.Game
import br.com.marcelo.azevedo.model.enums.CardColor
import br.com.marcelo.azevedo.model.enums.CardType

class SelectColorStepHandler(
    private val mediator: Mediator,
    private val game: Game,
): StepHandler(game) {

    private val cardUI = CardUI()

    override fun execute() {
        println(
            """
                
                
                
                
                
                
                
                
                
                
                Your cards are: ${cardUI.printCards(game.playerInTurn.cards)}
            """.trimIndent()
        )
        var cardColor: CardColor? = null
        while (cardColor == null) {
            try {
                println(cardUI.printCards(game.playerInTurn.cards))
                print("Please, selec a valid color ${game.playerInTurn.name} (red, blue, green or yellow): ")
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
        game.turnColor = cardColor
        if (game.lastCardPlayed().cardType == CardType.JOKER_PLUS_FOUR) mediator.notify(this, MediatorEvent.MAKE_PLAYER_GET_CARDS)
        else {
            game.isSpecialEffectActive = false
            mediator.notify(this, MediatorEvent.NEXT_TURN)
        }
    }
}