package br.com.marcelo.azevedo.ui

import br.com.marcelo.azevedo.exceptions.GetNewCardAndFisnishTurnException
import br.com.marcelo.azevedo.exceptions.InvalidCardIndexPlayed
import br.com.marcelo.azevedo.exceptions.InvalidCardPlayed

class ExceptionHandler {

    fun handlerWith(error: Exception) {
        when (error) {
            is InvalidCardIndexPlayed -> println(
                """
                        
                        
                        
                        
                        
                        
                        ## WARNING ##
                        Plase, select a valid index for your card!
                        #############
                """.trimIndent()
            )
            is InvalidCardPlayed -> println(
                """
                        
                        
                        
                        
                        
                        
                        ## WARNING ##
                        Please, select a valid card to play!
                        #############
                """.trimIndent()
            )
            is GetNewCardAndFisnishTurnException -> println(
                """
                        
                        
                        
                        
                        
                        
                        ## WARNING ##
                        You have no cards do play now, because of this you get a new card.
                        #############
                """.trimIndent()
            )
            else -> println(
                """
                        
                        
                        
                        
                        
                        
                        ## WARNING ##
                        A error has ocurred!
                        #############
                """.trimIndent()
            )
        }
    }

}