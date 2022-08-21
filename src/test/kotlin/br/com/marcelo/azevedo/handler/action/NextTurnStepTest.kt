package br.com.marcelo.azevedo.handler.action

import br.com.marcelo.azevedo.handler.StepHandlerTest
import br.com.marcelo.azevedo.mediator.MediatorEvent
import br.com.marcelo.azevedo.model.Card
import br.com.marcelo.azevedo.model.GameContext
import br.com.marcelo.azevedo.model.Player
import br.com.marcelo.azevedo.model.enums.CardColor
import br.com.marcelo.azevedo.model.enums.CardType
import br.com.marcelo.azevedo.model.enums.GameDirection
import br.com.marcelo.azevedo.util.generateGame
import io.mockk.verify
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

class NextTurnStepTest : StepHandlerTest() {

    @Test
    fun `Should call special effect verification after this step with game direction FORWARD`() {
        val cardPlayed = Card(
            cardType = CardType.REVERT,
            color = CardColor.BLUE,
            value = -1,
        )

        val gameContext: GameContext = generateGame(cardPlayed = cardPlayed)
        assertEquals(GameDirection.FORWARD, gameContext.direction)
        assertEquals(cardPlayed, gameContext.lastCardPlayed())
        val playerInTurn = gameContext.playerInTurn
        val nextPlayer = getNextPlayer(gameContext)
        val specialCardEffectStepHandler = NextTurnStep(mediator, gameContext)
        specialCardEffectStepHandler.execute()

        val playerInTurnAfterExecutation = gameContext.playerInTurn

        verify { mediator.notify(specialCardEffectStepHandler, MediatorEvent.UNDER_EFFECT_VERIFICATION) }
        assertEquals(cardPlayed, gameContext.lastCardPlayed())
        assertEquals(GameDirection.FORWARD, gameContext.direction)
        assertNotEquals(playerInTurn, playerInTurnAfterExecutation)
        assertEquals(nextPlayer, playerInTurnAfterExecutation)
    }

    @Test
    fun `Should call special effect verification after this step with game direction BACKWARD`() {
        val cardPlayed = Card(
            cardType = CardType.REVERT,
            color = CardColor.BLUE,
            value = -1,
        )

        val gameContext: GameContext = generateGame(cardPlayed = cardPlayed).copy(direction = GameDirection.BACKWARD)
        assertEquals(GameDirection.BACKWARD, gameContext.direction)
        assertEquals(cardPlayed, gameContext.lastCardPlayed())
        val playerInTurn = gameContext.playerInTurn
        val nextPlayer = getNextPlayer(gameContext)
        val specialCardEffectStepHandler = NextTurnStep(mediator, gameContext)
        specialCardEffectStepHandler.execute()

        val playerInTurnAfterExecutation = gameContext.playerInTurn

        verify { mediator.notify(specialCardEffectStepHandler, MediatorEvent.UNDER_EFFECT_VERIFICATION) }
        assertEquals(cardPlayed, gameContext.lastCardPlayed())
        assertEquals(GameDirection.BACKWARD, gameContext.direction)
        assertNotEquals(playerInTurn, playerInTurnAfterExecutation)
        assertEquals(nextPlayer, playerInTurnAfterExecutation)
    }

    @Test
    fun `Should call special effect verification after this step with game direction FORWARD and player is the last`() {
        val cardPlayed = Card(
            cardType = CardType.REVERT,
            color = CardColor.BLUE,
            value = -1,
        )

        val gameContext: GameContext = generateGame(cardPlayed = cardPlayed).run { this.copy(playerInTurn = this.players.last()) }
        assertEquals(GameDirection.FORWARD, gameContext.direction)
        assertEquals(cardPlayed, gameContext.lastCardPlayed())
        val playerInTurn = gameContext.playerInTurn
        val nextPlayer = getNextPlayer(gameContext)
        val specialCardEffectStepHandler = NextTurnStep(mediator, gameContext)
        specialCardEffectStepHandler.execute()

        val playerInTurnAfterExecutation = gameContext.playerInTurn

        verify { mediator.notify(specialCardEffectStepHandler, MediatorEvent.UNDER_EFFECT_VERIFICATION) }
        assertEquals(cardPlayed, gameContext.lastCardPlayed())
        assertEquals(GameDirection.FORWARD, gameContext.direction)
        assertNotEquals(playerInTurn, playerInTurnAfterExecutation)
        assertEquals(nextPlayer, playerInTurnAfterExecutation)
    }

    private fun getNextPlayer(gameContext: GameContext): Player =
        gameContext.players.indexOf(gameContext.playerInTurn).run {
            val nextIndex =
                if (gameContext.direction == GameDirection.FORWARD) this.inc()
                else this.dec()
            val index =
                if (nextIndex < 0) gameContext.players.size - 1
                else if (nextIndex >= gameContext.players.size) 0
                else nextIndex
            gameContext.players[index]
        }

}