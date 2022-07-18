package br.com.marcelo.azevedo.handler.action

import br.com.marcelo.azevedo.handler.StepHandlerTest
import br.com.marcelo.azevedo.mediator.MediatorEvent
import br.com.marcelo.azevedo.model.Card
import br.com.marcelo.azevedo.model.Game
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

        val game: Game = generateGame(cardPlayed = cardPlayed)
        assertEquals(false, game.isSpecialEffectActive)
        assertEquals(cardPlayed, game.lastCardPlayed())
        val playerBlockedStepHandler = PlayerBlockedStepHandler(mediator, game)
        playerBlockedStepHandler.execute()

        verify { mediator.notify(playerBlockedStepHandler, MediatorEvent.NEXT_TURN) }
        assertEquals(false, game.isSpecialEffectActive)
    }

    @Test
    fun `Should call next turn when special effect is active`() {
        val cardPlayed = Card(
            cardType = CardType.REVERT,
            color = CardColor.BLUE,
            value = -1,
        )

        val game: Game = generateGame(cardPlayed = cardPlayed).copy(isSpecialEffectActive = true)
        assertEquals(true, game.isSpecialEffectActive)
        assertEquals(cardPlayed, game.lastCardPlayed())
        val playerBlockedStepHandler = PlayerBlockedStepHandler(mediator, game)
        playerBlockedStepHandler.execute()

        verify { mediator.notify(playerBlockedStepHandler, MediatorEvent.NEXT_TURN) }
        assertEquals(false, game.isSpecialEffectActive)
    }

}