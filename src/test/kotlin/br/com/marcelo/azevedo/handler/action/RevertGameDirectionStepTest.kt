package br.com.marcelo.azevedo.handler.action

import br.com.marcelo.azevedo.handler.StepHandlerTest
import br.com.marcelo.azevedo.mediator.MediatorEvent
import br.com.marcelo.azevedo.model.Card
import br.com.marcelo.azevedo.model.GameContext
import br.com.marcelo.azevedo.model.enums.CardColor
import br.com.marcelo.azevedo.model.enums.CardType
import br.com.marcelo.azevedo.model.enums.GameDirection
import br.com.marcelo.azevedo.util.generateGame
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class RevertGameDirectionStepTest : StepHandlerTest() {

    @Test
    fun `Should revert game direction to BACKWARD if direction is FORWARD`() {
        val cardPlayed = Card(
            cardType = CardType.REVERT,
            color = CardColor.BLUE,
            value = -1,
        )

        val gameContext: GameContext = generateGame(cardPlayed = cardPlayed)
        gameContext.direction = GameDirection.FORWARD
        gameContext.isSpecialEffectActive = true

        assertEquals(true, gameContext.isSpecialEffectActive)
        assertEquals(cardPlayed, gameContext.lastCardPlayed())
        assertEquals(GameDirection.FORWARD, gameContext.direction)

        val revertGameDirectionStep = RevertGameDirectionStep(mediator, gameContext)
        revertGameDirectionStep.execute()

        verify { mediator.notify(MediatorEvent.NEXT_TURN) }
        assertEquals(false, gameContext.isSpecialEffectActive)
        assertEquals(GameDirection.BACKWARD, gameContext.direction)
    }

    @Test
    fun `Should revert game direction to FOWARD if direction is BACKWARD`() {
        val cardPlayed = Card(
            cardType = CardType.REVERT,
            color = CardColor.BLUE,
            value = -1,
        )

        val gameContext: GameContext = generateGame(cardPlayed = cardPlayed)
        gameContext.direction = GameDirection.BACKWARD
        gameContext.isSpecialEffectActive = true

        assertEquals(cardPlayed, gameContext.lastCardPlayed())
        assertEquals(GameDirection.BACKWARD, gameContext.direction)

        val revertGameDirectionStep = RevertGameDirectionStep(mediator, gameContext)
        revertGameDirectionStep.execute()

        verify { mediator.notify(MediatorEvent.NEXT_TURN) }
        assertEquals(false, gameContext.isSpecialEffectActive)
        assertEquals(GameDirection.FORWARD, gameContext.direction)
    }

}