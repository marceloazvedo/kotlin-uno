package br.com.marcelo.azevedo.handler.action

import br.com.marcelo.azevedo.handler.StepHandlerTest
import br.com.marcelo.azevedo.mediator.MediatorEvent
import br.com.marcelo.azevedo.model.Card
import br.com.marcelo.azevedo.model.GameContext
import br.com.marcelo.azevedo.model.enums.CardColor
import br.com.marcelo.azevedo.model.enums.CardType
import br.com.marcelo.azevedo.util.generateGame
import io.mockk.verify
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class PlayerBlockedStepHandlerTest: StepHandlerTest() {

    @Test
    fun `Should call next turn when special effect is not active`() {
        val cardPlayed = Card(
            cardType = CardType.REVERT,
            color = CardColor.BLUE,
            value = -1,
        )

        val gameContext: GameContext = generateGame(cardPlayed = cardPlayed)
        assertEquals(false, gameContext.isSpecialEffectActive)
        assertEquals(cardPlayed, gameContext.lastCardPlayed())
        val playerBlockedStepHandler = PlayerBlockedStepHandler(mediator, gameContext)
        playerBlockedStepHandler.execute()

        verify { mediator.notify(playerBlockedStepHandler, MediatorEvent.NEXT_TURN) }
        assertEquals(false, gameContext.isSpecialEffectActive)
    }

    @Test
    fun `Should call next turn when special effect is active`() {
        val cardPlayed = Card(
            cardType = CardType.REVERT,
            color = CardColor.BLUE,
            value = -1,
        )

        val gameContext: GameContext = generateGame(cardPlayed = cardPlayed).copy(isSpecialEffectActive = true)
        assertEquals(true, gameContext.isSpecialEffectActive)
        assertEquals(cardPlayed, gameContext.lastCardPlayed())
        val playerBlockedStepHandler = PlayerBlockedStepHandler(mediator, gameContext)
        playerBlockedStepHandler.execute()

        verify { mediator.notify(playerBlockedStepHandler, MediatorEvent.NEXT_TURN) }
        assertEquals(false, gameContext.isSpecialEffectActive)
    }

}