package br.com.marcelo.azevedo.handler.action

import br.com.marcelo.azevedo.handler.StepHandlerTest
import br.com.marcelo.azevedo.mediator.MediatorEvent
import br.com.marcelo.azevedo.model.Card
import br.com.marcelo.azevedo.model.GameContext
import br.com.marcelo.azevedo.model.enums.CardColor
import br.com.marcelo.azevedo.model.enums.CardType
import br.com.marcelo.azevedo.util.generateGame
import io.mockk.verify
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class ValidatePlayerHasCardStepHandlerTest : StepHandlerTest() {


    @Test
    fun `Should pass to chose card step handler when player has JOKER card`() {
        val cardPlayed = Card(
            cardType = CardType.PLUS_TWO,
            color = CardColor.GREEN,
            value = -1,
        )

        val gameContext: GameContext = generateGame(cardPlayed = cardPlayed)
        gameContext.turnColor = CardColor.GREEN
        gameContext.playerInTurn.cards = mutableListOf(
            Card(
                cardType = CardType.JOKER,
                color = CardColor.NO_COLOR,
                value = -1,
            )
        )

        Assertions.assertEquals(cardPlayed, gameContext.lastCardPlayed())

        val validatePlayerHasCardStepHandler = ValidatePlayerHasCardStepHandler(mediator, gameContext)
        validatePlayerHasCardStepHandler.execute()

        verify { mediator.notify(validatePlayerHasCardStepHandler, MediatorEvent.CHOSE_CARD) }
    }

    @Test
    fun `Should pass to chose card step handler when player has JOKER PLUS FOUR card`() {
        val cardPlayed = Card(
            cardType = CardType.PLUS_TWO,
            color = CardColor.GREEN,
            value = -1,
        )

        val gameContext: GameContext = generateGame(cardPlayed = cardPlayed)
        gameContext.turnColor = CardColor.GREEN
        gameContext.playerInTurn.cards = mutableListOf(
            Card(
                cardType = CardType.JOKER_PLUS_FOUR,
                color = CardColor.NO_COLOR,
                value = -1,
            )
        )

        Assertions.assertEquals(cardPlayed, gameContext.lastCardPlayed())

        val validatePlayerHasCardStepHandler = ValidatePlayerHasCardStepHandler(mediator, gameContext)
        validatePlayerHasCardStepHandler.execute()

        verify { mediator.notify(validatePlayerHasCardStepHandler, MediatorEvent.CHOSE_CARD) }
    }

    @Test
    fun `Should pass to chose card step handler when player has card with turn color`() {
        val cardPlayed = Card(
            cardType = CardType.PLUS_TWO,
            color = CardColor.GREEN,
            value = -1,
        )

        val gameContext: GameContext = generateGame(cardPlayed = cardPlayed)
        gameContext.turnColor = CardColor.GREEN
        gameContext.playerInTurn.cards = mutableListOf(
            Card(
                cardType = CardType.NUMBER,
                color = CardColor.GREEN,
                value = 9,
            )
        )

        Assertions.assertEquals(cardPlayed, gameContext.lastCardPlayed())

        val validatePlayerHasCardStepHandler = ValidatePlayerHasCardStepHandler(mediator, gameContext)
        validatePlayerHasCardStepHandler.execute()

        verify { mediator.notify(validatePlayerHasCardStepHandler, MediatorEvent.CHOSE_CARD) }
    }

    @Test
    fun `Should pass to chose card step handler when player has PLUS TWO with turn same type`() {
        val cardPlayed = Card(
            cardType = CardType.PLUS_TWO,
            color = CardColor.RED,
            value = -1,
        )

        val gameContext: GameContext = generateGame(cardPlayed = cardPlayed)
        gameContext.turnColor = CardColor.RED
        gameContext.playerInTurn.cards = mutableListOf(
            Card(
                cardType = CardType.PLUS_TWO,
                color = CardColor.GREEN,
                value = -1,
            )
        )

        Assertions.assertEquals(cardPlayed, gameContext.lastCardPlayed())

        val validatePlayerHasCardStepHandler = ValidatePlayerHasCardStepHandler(mediator, gameContext)
        validatePlayerHasCardStepHandler.execute()

        verify { mediator.notify(validatePlayerHasCardStepHandler, MediatorEvent.CHOSE_CARD) }
    }

    @Test
    fun `Should pass to chose card step handler when player has NUMBER card with turn same value`() {
        val cardPlayed = Card(
            cardType = CardType.NUMBER,
            color = CardColor.RED,
            value = 9,
        )

        val gameContext: GameContext = generateGame(cardPlayed = cardPlayed)
        gameContext.turnColor = CardColor.RED
        gameContext.playerInTurn.cards = mutableListOf(
            Card(
                cardType = CardType.NUMBER,
                color = CardColor.GREEN,
                value = 9,
            )
        )

        Assertions.assertEquals(cardPlayed, gameContext.lastCardPlayed())

        val validatePlayerHasCardStepHandler = ValidatePlayerHasCardStepHandler(mediator, gameContext)
        validatePlayerHasCardStepHandler.execute()

        verify { mediator.notify(validatePlayerHasCardStepHandler, MediatorEvent.CHOSE_CARD) }
    }

    @Test
    fun `Should pass to get card step handler when player has NUMBER card with turn different value and color`() {
        val cardPlayed = Card(
            cardType = CardType.NUMBER,
            color = CardColor.RED,
            value = 9,
        )

        val gameContext: GameContext = generateGame(cardPlayed = cardPlayed)
        gameContext.turnColor = CardColor.RED
        gameContext.playerInTurn.cards = mutableListOf(
            Card(
                cardType = CardType.NUMBER,
                color = CardColor.GREEN,
                value = 7,
            )
        )

        Assertions.assertEquals(cardPlayed, gameContext.lastCardPlayed())

        val validatePlayerHasCardStepHandler = ValidatePlayerHasCardStepHandler(mediator, gameContext)
        validatePlayerHasCardStepHandler.execute()

        verify { mediator.notify(validatePlayerHasCardStepHandler, MediatorEvent.GET_CARD) }
    }

    @Test
    fun `Should pass to get card step handler when player has BLOCK card with turn different type and color`() {
        val cardPlayed = Card(
            cardType = CardType.BLOCK,
            color = CardColor.RED,
            value = -1,
        )

        val gameContext: GameContext = generateGame(cardPlayed = cardPlayed)
        gameContext.turnColor = CardColor.RED
        gameContext.playerInTurn.cards = mutableListOf(
            Card(
                cardType = CardType.PLUS_TWO,
                color = CardColor.GREEN,
                value = -1,
            )
        )

        Assertions.assertEquals(cardPlayed, gameContext.lastCardPlayed())

        val validatePlayerHasCardStepHandler = ValidatePlayerHasCardStepHandler(mediator, gameContext)
        validatePlayerHasCardStepHandler.execute()

        verify { mediator.notify(validatePlayerHasCardStepHandler, MediatorEvent.GET_CARD) }
    }

    @Test
    fun `Should pass to chose card step handler when player has BLOCK card with turn different color but same type`() {
        val cardPlayed = Card(
            cardType = CardType.BLOCK,
            color = CardColor.RED,
            value = -1,
        )

        val gameContext: GameContext = generateGame(cardPlayed = cardPlayed)
        gameContext.turnColor = CardColor.RED
        gameContext.playerInTurn.cards = mutableListOf(
            Card(
                cardType = CardType.BLOCK,
                color = CardColor.GREEN,
                value = -1,
            )
        )

        Assertions.assertEquals(cardPlayed, gameContext.lastCardPlayed())

        val validatePlayerHasCardStepHandler = ValidatePlayerHasCardStepHandler(mediator, gameContext)
        validatePlayerHasCardStepHandler.execute()

        verify { mediator.notify(validatePlayerHasCardStepHandler, MediatorEvent.CHOSE_CARD) }
    }

}