package br.com.marcelo.azevedo.handler.input

import br.com.marcelo.azevedo.ui.CardUI
import br.com.marcelo.azevedo.handler.StepHandler
import br.com.marcelo.azevedo.mediator.Mediator
import br.com.marcelo.azevedo.mediator.MediatorEvent
import br.com.marcelo.azevedo.model.Card
import br.com.marcelo.azevedo.model.GameContext

class ChoseCardStepHandler(
    val mediator: Mediator,
    val gameContext: GameContext,
) : StepHandler(gameContext, mediator) {

    private val cardUI = CardUI()

    override fun execute() {
        println(
            """
                
                
                
                
                
                
                
                
                
                
                The turn card is:
            """.trimIndent()
        )
        println(cardUI.printCards(listOf(gameContext.lastCardPlayed())))
        println("Is turn of ${gameContext.playerInTurn.name}")
        println("${gameContext.remainingCards.size} cards left.")
        var cardSelected: Card? = null
        while (cardSelected == null) {
            try {
                println(cardUI.printCards(gameContext.playerInTurn.cards))
                println("The turn color is: ${gameContext.turnColor.name}")
                print("Please, select your card ${gameContext.playerInTurn.name}: ")
                val cardIndex = readln().toInt() - 1
                cardSelected = gameContext.playerInTurn.cards[cardIndex]
            } catch (error: Exception) {
                println(
                    """
                        
                        
                        
                        
                        
                        
                        ## WARNING ##
                        Plase, select a valid index for your card!
                        #############
                """.trimIndent()
                )
            }
        }
        gameContext.cardSelectToPlay = cardSelected
        mediator.notify(this, MediatorEvent.VALIDATE_CARD_PLAYED)
    }
}