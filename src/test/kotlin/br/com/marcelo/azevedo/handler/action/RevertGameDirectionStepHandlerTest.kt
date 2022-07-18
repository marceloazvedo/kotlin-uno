package br.com.marcelo.azevedo.handler.action

import br.com.marcelo.azevedo.handler.StepHandlerTest
import br.com.marcelo.azevedo.mediator.MediatorEvent
import br.com.marcelo.azevedo.model.Card
import br.com.marcelo.azevedo.model.Game
import br.com.marcelo.azevedo.model.enums.CardColor
import br.com.marcelo.azevedo.model.enums.CardType
import br.com.marcelo.azevedo.model.enums.GameDirection
import br.com.marcelo.azevedo.util.generateGame
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class RevertGameDirectionStepHandlerTest : StepHandlerTest() {

    @Test
    fun `Should revert game direction to BACKWARD if direction is FORWARD`() {
        val cardPlayed = Card(
            cardType = CardType.REVERT,
            color = CardColor.BLUE,
            value = -1,
        )

        val game: Game = generateGame(cardPlayed = cardPlayed)
        game.direction = GameDirection.FORWARD
        game.isSpecialEffectActive = true

        assertEquals(true, game.isSpecialEffectActive)
        assertEquals(cardPlayed, game.lastCardPlayed())
        assertEquals(GameDirection.FORWARD, game.direction)

        val revertGameDirectionStepHandler = RevertGameDirectionStepHandler(mediator, game)
        revertGameDirectionStepHandler.execute()

        verify { mediator.notify(revertGameDirectionStepHandler, MediatorEvent.NEXT_TURN) }
        assertEquals(false, game.isSpecialEffectActive)
        assertEquals(GameDirection.BACKWARD, game.direction)
    }

    @Test
    fun `Should revert game direction to FOWARD if direction is BACKWARD`() {
        val cardPlayed = Card(
            cardType = CardType.REVERT,
            color = CardColor.BLUE,
            value = -1,
        )

        val game: Game = generateGame(cardPlayed = cardPlayed)
        game.direction = GameDirection.BACKWARD
        game.isSpecialEffectActive = true

        assertEquals(cardPlayed, game.lastCardPlayed())
        assertEquals(GameDirection.BACKWARD, game.direction)

        val revertGameDirectionStepHandler = RevertGameDirectionStepHandler(mediator, game)
        revertGameDirectionStepHandler.execute()

        verify { mediator.notify(revertGameDirectionStepHandler, MediatorEvent.NEXT_TURN) }
        assertEquals(false, game.isSpecialEffectActive)
        assertEquals(GameDirection.FORWARD, game.direction)
    }

}