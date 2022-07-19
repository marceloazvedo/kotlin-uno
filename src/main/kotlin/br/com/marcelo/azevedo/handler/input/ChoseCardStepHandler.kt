package br.com.marcelo.azevedo.handler.input

import br.com.marcelo.azevedo.ui.CardUI
import br.com.marcelo.azevedo.handler.StepHandler
import br.com.marcelo.azevedo.mediator.Mediator
import br.com.marcelo.azevedo.mediator.MediatorEvent
import br.com.marcelo.azevedo.model.Card
import br.com.marcelo.azevedo.model.Game

class ChoseCardStepHandler(
    val mediator: Mediator,
    val game: Game,
) : StepHandler(game, mediator) {

    private val cardUI = CardUI()

    override fun execute() {
        println(
            """
                
                
                
                
                
                
                
                
                
                
                The turn card is:
            """.trimIndent()
        )
        println(cardUI.printCards(listOf(game.lastCardPlayed())))
        println("Is turn of ${game.playerInTurn.name}")
        println("${game.remainingCards.size} cards left.")
        var cardSelected: Card? = null
        while (cardSelected == null) {
            try {
                println(cardUI.printCards(game.playerInTurn.cards))
                println("The turn color is: ${game.turnColor.name}")
                print("Please, select your card ${game.playerInTurn.name}: ")
                val cardIndex = readln().toInt() - 1
                cardSelected = game.playerInTurn.cards[cardIndex]
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
        game.cardSelectToPlay = cardSelected
        mediator.notify(this, MediatorEvent.VALIDATE_CARD_PLAYED)
    }
}