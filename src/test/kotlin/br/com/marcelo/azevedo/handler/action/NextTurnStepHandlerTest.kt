package br.com.marcelo.azevedo.handler.action

import br.com.marcelo.azevedo.handler.StepHandlerTest
import br.com.marcelo.azevedo.mediator.MediatorEvent
import br.com.marcelo.azevedo.model.Card
import br.com.marcelo.azevedo.model.Game
import br.com.marcelo.azevedo.model.Player
import br.com.marcelo.azevedo.model.enums.CardColor
import br.com.marcelo.azevedo.model.enums.CardType
import br.com.marcelo.azevedo.model.enums.GameDirection
import br.com.marcelo.azevedo.util.generateGame
import io.mockk.verify
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

class NextTurnStepHandlerTest : StepHandlerTest() {

    @Test
    fun `Should call special effect verification after this step with game direction FORWARD`() {
        val cardPlayed = Card(
            cardType = CardType.REVERT,
            color = CardColor.BLUE,
            value = -1,
        )

        val game: Game = generateGame(cardPlayed = cardPlayed)
        assertEquals(GameDirection.FORWARD, game.direction)
        assertEquals(cardPlayed, game.lastCardPlayed())
        val playerInTurn = game.playerInTurn
        val nextPlayer = getNextPlayer(game)
        val specialCardEffectStepHandler = NextTurnStepHandler(mediator, game)
        specialCardEffectStepHandler.execute()

        val playerInTurnAfterExecutation = game.playerInTurn

        verify { mediator.notify(specialCardEffectStepHandler, MediatorEvent.UNDER_EFFECT_VERIFICATION) }
        assertEquals(cardPlayed, game.lastCardPlayed())
        assertEquals(GameDirection.FORWARD, game.direction)
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

        val game: Game = generateGame(cardPlayed = cardPlayed).copy(direction = GameDirection.BACKWARD)
        assertEquals(GameDirection.BACKWARD, game.direction)
        assertEquals(cardPlayed, game.lastCardPlayed())
        val playerInTurn = game.playerInTurn
        val nextPlayer = getNextPlayer(game)
        val specialCardEffectStepHandler = NextTurnStepHandler(mediator, game)
        specialCardEffectStepHandler.execute()

        val playerInTurnAfterExecutation = game.playerInTurn

        verify { mediator.notify(specialCardEffectStepHandler, MediatorEvent.UNDER_EFFECT_VERIFICATION) }
        assertEquals(cardPlayed, game.lastCardPlayed())
        assertEquals(GameDirection.BACKWARD, game.direction)
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

        val game: Game = generateGame(cardPlayed = cardPlayed).run { this.copy(playerInTurn = this.players.last()) }
        assertEquals(GameDirection.FORWARD, game.direction)
        assertEquals(cardPlayed, game.lastCardPlayed())
        val playerInTurn = game.playerInTurn
        val nextPlayer = getNextPlayer(game)
        val specialCardEffectStepHandler = NextTurnStepHandler(mediator, game)
        specialCardEffectStepHandler.execute()

        val playerInTurnAfterExecutation = game.playerInTurn

        verify { mediator.notify(specialCardEffectStepHandler, MediatorEvent.UNDER_EFFECT_VERIFICATION) }
        assertEquals(cardPlayed, game.lastCardPlayed())
        assertEquals(GameDirection.FORWARD, game.direction)
        assertNotEquals(playerInTurn, playerInTurnAfterExecutation)
        assertEquals(nextPlayer, playerInTurnAfterExecutation)
    }

    private fun getNextPlayer(game: Game): Player =
        game.players.indexOf(game.playerInTurn).run {
            val nextIndex =
                if (game.direction == GameDirection.FORWARD) this.inc()
                else this.dec()
            val index =
                if (nextIndex < 0) game.players.size - 1
                else if (nextIndex >= game.players.size) 0
                else nextIndex
            game.players[index]
        }

}