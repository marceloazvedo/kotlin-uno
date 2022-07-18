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

class SpecialCardEffectStepHandlerTest: StepHandlerTest() {

    @Test
    fun `Should revert the game direction after this step handler`() {
        val cardPlayed = Card(
            cardType = CardType.REVERT,
            color = CardColor.BLUE,
            value = -1,
        )

        val game: Game = generateGame(cardPlayed = cardPlayed)
        assertEquals(false, game.isSpecialEffectActive)
        assertEquals(cardPlayed, game.lastCardPlayed())

        val specialCardEffectStepHandler = SpecialCardEffectStepHandler(mediator, game)
        specialCardEffectStepHandler.execute()

        verify { mediator.notify(specialCardEffectStepHandler, MediatorEvent.REVERT_GAME_DIRECTION) }
        assertEquals(true, game.isSpecialEffectActive)
        assertEquals(cardPlayed, game.lastCardPlayed())
    }

    @Test
    fun `Should block player after this step handler`() {
        val cardPlayed = Card(
            cardType = CardType.BLOCK,
            color = CardColor.BLUE,
            value = -1,
        )

        val game: Game = generateGame(cardPlayed = cardPlayed)
        assertEquals(false, game.isSpecialEffectActive)
        assertEquals(cardPlayed, game.lastCardPlayed())

        val specialCardEffectStepHandler = SpecialCardEffectStepHandler(mediator, game)
        specialCardEffectStepHandler.execute()

        verify { mediator.notify(specialCardEffectStepHandler, MediatorEvent.PLAYER_BLOCKED) }
        assertEquals(true, game.isSpecialEffectActive)
        assertEquals(cardPlayed, game.lastCardPlayed())
    }

    @Test
    fun `Should make player get cards after this step handler when card in turn is plus two`() {
        val cardPlayed = Card(
            cardType = CardType.PLUS_TWO,
            color = CardColor.BLUE,
            value = -1,
        )

        val game: Game = generateGame(cardPlayed = cardPlayed)
        assertEquals(false, game.isSpecialEffectActive)
        assertEquals(cardPlayed, game.lastCardPlayed())

        val specialCardEffectStepHandler = SpecialCardEffectStepHandler(mediator, game)
        specialCardEffectStepHandler.execute()

        verify { mediator.notify(specialCardEffectStepHandler, MediatorEvent.MAKE_PLAYER_GET_CARDS) }
        assertEquals(true, game.isSpecialEffectActive)
        assertEquals(cardPlayed, game.lastCardPlayed())
    }

    @Test
    fun `Should make player select turn color after this step handler when card in turn is joker plus four`() {
        val cardPlayed = Card(
            cardType = CardType.JOKER_PLUS_FOUR,
            color = CardColor.NO_COLOR,
            value = -1,
        )

        val game: Game = generateGame(cardPlayed = cardPlayed)
        assertEquals(false, game.isSpecialEffectActive)
        assertEquals(cardPlayed, game.lastCardPlayed())

        val specialCardEffectStepHandler = SpecialCardEffectStepHandler(mediator, game)
        specialCardEffectStepHandler.execute()

        verify { mediator.notify(specialCardEffectStepHandler, MediatorEvent.SELECT_COLOR_GAME) }
        assertEquals(true, game.isSpecialEffectActive)
        assertEquals(cardPlayed, game.lastCardPlayed())
    }

    @Test
    fun `Should make player select turn color after this step handler when card in turn is joker`() {
        val cardPlayed = Card(
            cardType = CardType.JOKER,
            color = CardColor.NO_COLOR,
            value = -1,
        )

        val game: Game = generateGame(cardPlayed = cardPlayed)
        assertEquals(false, game.isSpecialEffectActive)
        assertEquals(cardPlayed, game.lastCardPlayed())

        val specialCardEffectStepHandler = SpecialCardEffectStepHandler(mediator, game)
        specialCardEffectStepHandler.execute()

        verify { mediator.notify(specialCardEffectStepHandler, MediatorEvent.SELECT_COLOR_GAME) }
        assertEquals(false, game.isSpecialEffectActive)
        assertEquals(cardPlayed, game.lastCardPlayed())
    }

    @Test
    fun `Should pass turn to the next player`() {
        val cardPlayed = Card(
            cardType = CardType.NUMBER,
            color = CardColor.BLUE,
            value = 9,
        )

        val game: Game = generateGame(cardPlayed = cardPlayed)
        assertEquals(false, game.isSpecialEffectActive)
        assertEquals(cardPlayed, game.lastCardPlayed())

        val specialCardEffectStepHandler = SpecialCardEffectStepHandler(mediator, game)
        specialCardEffectStepHandler.execute()

        verify { mediator.notify(specialCardEffectStepHandler, MediatorEvent.NEXT_TURN) }
        assertEquals(false, game.isSpecialEffectActive)
        assertEquals(cardPlayed, game.lastCardPlayed())
    }

}