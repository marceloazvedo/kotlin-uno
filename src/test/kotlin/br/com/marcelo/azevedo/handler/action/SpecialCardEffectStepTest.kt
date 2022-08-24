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

class SpecialCardEffectStepTest: StepHandlerTest() {

    @Test
    fun `Should revert the game direction after this step handler`() {
        val cardPlayed = Card(
            cardType = CardType.REVERT,
            color = CardColor.BLUE,
            value = -1,
        )

        val gameContext: GameContext = generateGame(cardPlayed = cardPlayed)
        assertEquals(false, gameContext.isSpecialEffectActive)
        assertEquals(cardPlayed, gameContext.lastCardPlayed())

        val specialCardEffectStep = SpecialCardEffectStep(mediator, gameContext)
        specialCardEffectStep.execute()

        verify { mediator.notify(MediatorEvent.REVERT_GAME_DIRECTION) }
        assertEquals(true, gameContext.isSpecialEffectActive)
        assertEquals(cardPlayed, gameContext.lastCardPlayed())
    }

    @Test
    fun `Should block player after this step handler`() {
        val cardPlayed = Card(
            cardType = CardType.BLOCK,
            color = CardColor.BLUE,
            value = -1,
        )

        val gameContext: GameContext = generateGame(cardPlayed = cardPlayed)
        assertEquals(false, gameContext.isSpecialEffectActive)
        assertEquals(cardPlayed, gameContext.lastCardPlayed())

        val specialCardEffectStep = SpecialCardEffectStep(mediator, gameContext)
        specialCardEffectStep.execute()

        verify { mediator.notify(MediatorEvent.NEXT_TURN) }
        assertEquals(true, gameContext.isSpecialEffectActive)
        assertEquals(cardPlayed, gameContext.lastCardPlayed())
    }

    @Test
    fun `Should make player get cards after this step handler when card in turn is plus two`() {
        val cardPlayed = Card(
            cardType = CardType.PLUS_TWO,
            color = CardColor.BLUE,
            value = -1,
        )

        val gameContext: GameContext = generateGame(cardPlayed = cardPlayed)
        assertEquals(false, gameContext.isSpecialEffectActive)
        assertEquals(cardPlayed, gameContext.lastCardPlayed())

        val specialCardEffectStep = SpecialCardEffectStep(mediator, gameContext)
        specialCardEffectStep.execute()

        verify { mediator.notify(MediatorEvent.NEXT_TURN, MediatorEvent.MAKE_PLAYER_GET_CARDS) }
        assertEquals(true, gameContext.isSpecialEffectActive)
        assertEquals(cardPlayed, gameContext.lastCardPlayed())
    }

    @Test
    fun `Should make player select turn color after this step handler when card in turn is joker plus four`() {
        val cardPlayed = Card(
            cardType = CardType.JOKER_PLUS_FOUR,
            color = CardColor.NO_COLOR,
            value = -1,
        )

        val gameContext: GameContext = generateGame(cardPlayed = cardPlayed)
        assertEquals(false, gameContext.isSpecialEffectActive)
        assertEquals(cardPlayed, gameContext.lastCardPlayed())

        val specialCardEffectStep = SpecialCardEffectStep(mediator, gameContext)
        specialCardEffectStep.execute()

        verify { mediator.notify(MediatorEvent.SELECT_COLOR_GAME) }
        assertEquals(true, gameContext.isSpecialEffectActive)
        assertEquals(cardPlayed, gameContext.lastCardPlayed())
    }

    @Test
    fun `Should make player select turn color after this step handler when card in turn is joker`() {
        val cardPlayed = Card(
            cardType = CardType.JOKER,
            color = CardColor.NO_COLOR,
            value = -1,
        )

        val gameContext: GameContext = generateGame(cardPlayed = cardPlayed)
        assertEquals(false, gameContext.isSpecialEffectActive)
        assertEquals(cardPlayed, gameContext.lastCardPlayed())

        val specialCardEffectStep = SpecialCardEffectStep(mediator, gameContext)
        specialCardEffectStep.execute()

        verify { mediator.notify(MediatorEvent.SELECT_COLOR_GAME) }
        assertEquals(false, gameContext.isSpecialEffectActive)
        assertEquals(cardPlayed, gameContext.lastCardPlayed())
    }

    @Test
    fun `Should pass turn to the next player`() {
        val cardPlayed = Card(
            cardType = CardType.NUMBER,
            color = CardColor.BLUE,
            value = 9,
        )

        val gameContext: GameContext = generateGame(cardPlayed = cardPlayed)
        assertEquals(false, gameContext.isSpecialEffectActive)
        assertEquals(cardPlayed, gameContext.lastCardPlayed())

        val specialCardEffectStep = SpecialCardEffectStep(mediator, gameContext)
        specialCardEffectStep.execute()

        verify { mediator.notify(MediatorEvent.NEXT_TURN) }
        assertEquals(false, gameContext.isSpecialEffectActive)
        assertEquals(cardPlayed, gameContext.lastCardPlayed())
    }

}