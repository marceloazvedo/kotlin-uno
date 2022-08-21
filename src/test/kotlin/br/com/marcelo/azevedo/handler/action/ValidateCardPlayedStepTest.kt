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

class ValidateCardPlayedStepTest : StepHandlerTest() {

    @Test
    fun `Should pass to chose card step handler if has no card selected`() {
        val cardPlayed = Card(
            cardType = CardType.JOKER,
            color = CardColor.NO_COLOR,
            value = -1,
        )

        val gameContext: GameContext = generateGame(cardPlayed = cardPlayed)
        gameContext.cardSelectToPlay = null

        Assertions.assertEquals(cardPlayed, gameContext.lastCardPlayed())

        val validateCardPlayedStepStepHandler = ValidateCardPlayedStep(mediator, gameContext)
        validateCardPlayedStepStepHandler.execute()

        verify { mediator.notify(validateCardPlayedStepStepHandler, MediatorEvent.CHOSE_CARD) }
    }

    @Test
    fun `Should pass to chose card step handler card selected is not valid`() {
        val cardPlayed = Card(
            cardType = CardType.PLUS_TWO,
            color = CardColor.BLUE,
            value = -1,
        )

        val gameContext: GameContext = generateGame(cardPlayed = cardPlayed)
        gameContext.cardSelectToPlay = Card(
            cardType = CardType.PLUS_TWO,
            color = CardColor.RED,
            value = -1,
        )

        Assertions.assertEquals(cardPlayed, gameContext.lastCardPlayed())

        val validateCardPlayedStepStepHandler = ValidateCardPlayedStep(mediator, gameContext)
        validateCardPlayedStepStepHandler.execute()

        verify { mediator.notify(validateCardPlayedStepStepHandler, MediatorEvent.ACTIVATE_SPECIAL_CARD_EFFECT) }
    }

    @Test
    fun `Should pass to chose card step handler when card selected is NUMBER and is not valid because of the color`() {
        val cardPlayed = Card(
            cardType = CardType.JOKER,
            color = CardColor.NO_COLOR,
            value = -1,
        )

        val gameContext: GameContext = generateGame(cardPlayed = cardPlayed)
        gameContext.turnColor = CardColor.GREEN
        gameContext.cardSelectToPlay = Card(
            cardType = CardType.NUMBER,
            color = CardColor.BLUE,
            value = 9,
        )

        Assertions.assertEquals(cardPlayed, gameContext.lastCardPlayed())

        val validateCardPlayedStepStepHandler = ValidateCardPlayedStep(mediator, gameContext)
        validateCardPlayedStepStepHandler.execute()

        verify { mediator.notify(validateCardPlayedStepStepHandler, MediatorEvent.CHOSE_CARD) }
    }

    @Test
    fun `Should pass to next turn step handler when card selected is NUMBER and is valid because of the value`() {
        val cardPlayed = Card(
            cardType = CardType.NUMBER,
            color = CardColor.RED,
            value = 9,
        )

        val gameContext: GameContext = generateGame(cardPlayed = cardPlayed)
        gameContext.turnColor = CardColor.RED
        gameContext.cardSelectToPlay = Card(
            cardType = CardType.NUMBER,
            color = CardColor.BLUE,
            value = 9,
        )

        Assertions.assertEquals(cardPlayed, gameContext.lastCardPlayed())

        val validateCardPlayedStepStepHandler = ValidateCardPlayedStep(mediator, gameContext)
        validateCardPlayedStepStepHandler.execute()

        verify { mediator.notify(validateCardPlayedStepStepHandler, MediatorEvent.NEXT_TURN) }
    }

    @Test
    fun `Should pass to next turn step handler when card selected is NUMBER and is valid because of the color`() {
        val cardPlayed = Card(
            cardType = CardType.NUMBER,
            color = CardColor.RED,
            value = 1,
        )

        val gameContext: GameContext = generateGame(cardPlayed = cardPlayed)
        gameContext.turnColor = CardColor.RED
        gameContext.cardSelectToPlay = Card(
            cardType = CardType.NUMBER,
            color = CardColor.RED,
            value = 9,
        )

        Assertions.assertEquals(cardPlayed, gameContext.lastCardPlayed())

        val validateCardPlayedStepStepHandler = ValidateCardPlayedStep(mediator, gameContext)
        validateCardPlayedStepStepHandler.execute()

        verify { mediator.notify(validateCardPlayedStepStepHandler, MediatorEvent.NEXT_TURN) }
    }

    @Test
    fun `Should pass to chose card step handler when card selected is BLOCK and is not valid because of the color`() {
        val cardPlayed = Card(
            cardType = CardType.JOKER,
            color = CardColor.NO_COLOR,
            value = -1,
        )

        val gameContext: GameContext = generateGame(cardPlayed = cardPlayed)
        gameContext.turnColor = CardColor.GREEN
        gameContext.cardSelectToPlay = Card(
            cardType = CardType.BLOCK,
            color = CardColor.BLUE,
            value = -1,
        )

        Assertions.assertEquals(cardPlayed, gameContext.lastCardPlayed())

        val validateCardPlayedStepStepHandler = ValidateCardPlayedStep(mediator, gameContext)
        validateCardPlayedStepStepHandler.execute()

        verify { mediator.notify(validateCardPlayedStepStepHandler, MediatorEvent.CHOSE_CARD) }
    }

    @Test
    fun `Should pass to chose card step handler when card selected is REVERT and is not valid because of the color`() {
        val cardPlayed = Card(
            cardType = CardType.JOKER,
            color = CardColor.NO_COLOR,
            value = -1,
        )

        val gameContext: GameContext = generateGame(cardPlayed = cardPlayed)
        gameContext.turnColor = CardColor.GREEN
        gameContext.cardSelectToPlay = Card(
            cardType = CardType.REVERT,
            color = CardColor.BLUE,
            value = -1,
        )

        Assertions.assertEquals(cardPlayed, gameContext.lastCardPlayed())

        val validateCardPlayedStepStepHandler = ValidateCardPlayedStep(mediator, gameContext)
        validateCardPlayedStepStepHandler.execute()

        verify { mediator.notify(validateCardPlayedStepStepHandler, MediatorEvent.CHOSE_CARD) }
    }

    @Test
    fun `Should pass to active special card step handler when card selected is REVERT and is valid`() {
        val cardPlayed = Card(
            cardType = CardType.REVERT,
            color = CardColor.RED,
            value = -1,
        )

        val gameContext: GameContext = generateGame(cardPlayed = cardPlayed)
        gameContext.turnColor = CardColor.RED
        gameContext.cardSelectToPlay = Card(
            cardType = CardType.REVERT,
            color = CardColor.BLUE,
            value = -1,
        )

        Assertions.assertEquals(cardPlayed, gameContext.lastCardPlayed())

        val validateCardPlayedStepStepHandler = ValidateCardPlayedStep(mediator, gameContext)
        validateCardPlayedStepStepHandler.execute()

        verify { mediator.notify(validateCardPlayedStepStepHandler, MediatorEvent.ACTIVATE_SPECIAL_CARD_EFFECT) }
    }

    @Test
    fun `Should pass to end game step handler when card selected is the last of player`() {
        val cardPlayed = Card(
            cardType = CardType.NUMBER,
            color = CardColor.RED,
            value = 9,
        )

        val gameContext: GameContext = generateGame(cardPlayed = cardPlayed)
        gameContext.turnColor = CardColor.RED
        val cardSelectedToPlay = Card(
            cardType = CardType.NUMBER,
            color = CardColor.RED,
            value = 7,
        )
        gameContext.cardSelectToPlay = cardSelectedToPlay
        gameContext.playerInTurn.cards = mutableListOf(cardSelectedToPlay)

        Assertions.assertEquals(cardPlayed, gameContext.lastCardPlayed())

        val validateCardPlayedStepStepHandler = ValidateCardPlayedStep(mediator, gameContext)
        validateCardPlayedStepStepHandler.execute()

        verify { mediator.notify(validateCardPlayedStepStepHandler, MediatorEvent.END_GAME) }
    }

    @Test
    fun `Should pass to chose card step handler when card selected is invalid because the color and type`() {
        val cardPlayed = Card(
            cardType = CardType.NUMBER,
            color = CardColor.RED,
            value = 9,
        )

        val gameContext: GameContext = generateGame(cardPlayed = cardPlayed)
        gameContext.turnColor = CardColor.GREEN
        val cardSelectedToPlay = Card(
            cardType = CardType.BLOCK,
            color = CardColor.BLUE,
            value = -1,
        )
        gameContext.cardSelectToPlay = cardSelectedToPlay
        gameContext.playerInTurn.cards = mutableListOf(cardSelectedToPlay)

        Assertions.assertEquals(cardPlayed, gameContext.lastCardPlayed())

        val validateCardPlayedStepStepHandler = ValidateCardPlayedStep(mediator, gameContext)
        validateCardPlayedStepStepHandler.execute()

        verify { mediator.notify(validateCardPlayedStepStepHandler, MediatorEvent.CHOSE_CARD) }
    }

    @Test
    fun `Should pass to chose card step handler when card selected is PLUS TWO and invalid because the color`() {
        val cardPlayed = Card(
            cardType = CardType.NUMBER,
            color = CardColor.GREEN,
            value = -1,
        )

        val gameContext: GameContext = generateGame(cardPlayed = cardPlayed)
        gameContext.turnColor = CardColor.GREEN
        val cardSelectedToPlay = Card(
            cardType = CardType.PLUS_TWO,
            color = CardColor.BLUE,
            value = -1,
        )
        gameContext.cardSelectToPlay = cardSelectedToPlay
        gameContext.playerInTurn.cards = mutableListOf(cardSelectedToPlay)

        Assertions.assertEquals(cardPlayed, gameContext.lastCardPlayed())

        val validateCardPlayedStepStepHandler = ValidateCardPlayedStep(mediator, gameContext)
        validateCardPlayedStepStepHandler.execute()

        verify { mediator.notify(validateCardPlayedStepStepHandler, MediatorEvent.CHOSE_CARD) }
    }

    @Test
    fun `Should pass to select color step handler when card selected is JOKER PLUS FOUR`() {
        val cardPlayed = Card(
            cardType = CardType.PLUS_TWO,
            color = CardColor.GREEN,
            value = -1,
        )

        val gameContext: GameContext = generateGame(cardPlayed = cardPlayed)
        gameContext.turnColor = CardColor.GREEN
        val cardSelectedToPlay = Card(
            cardType = CardType.JOKER_PLUS_FOUR,
            color = CardColor.NO_COLOR,
            value = -1,
        )
        gameContext.cardSelectToPlay = cardSelectedToPlay

        Assertions.assertEquals(cardPlayed, gameContext.lastCardPlayed())

        val validateCardPlayedStepStepHandler = ValidateCardPlayedStep(mediator, gameContext)
        validateCardPlayedStepStepHandler.execute()

        verify { mediator.notify(validateCardPlayedStepStepHandler, MediatorEvent.ACTIVATE_SPECIAL_CARD_EFFECT) }
    }

    @Test
    fun `Should pass to select color step handler when card selected is JOKER`() {
        val cardPlayed = Card(
            cardType = CardType.PLUS_TWO,
            color = CardColor.GREEN,
            value = -1,
        )

        val gameContext: GameContext = generateGame(cardPlayed = cardPlayed)
        gameContext.turnColor = CardColor.GREEN
        val cardSelectedToPlay = Card(
            cardType = CardType.JOKER,
            color = CardColor.NO_COLOR,
            value = -1,
        )
        gameContext.cardSelectToPlay = cardSelectedToPlay

        Assertions.assertEquals(cardPlayed, gameContext.lastCardPlayed())

        val validateCardPlayedStepStepHandler = ValidateCardPlayedStep(mediator, gameContext)
        validateCardPlayedStepStepHandler.execute()

        verify { mediator.notify(validateCardPlayedStepStepHandler, MediatorEvent.ACTIVATE_SPECIAL_CARD_EFFECT) }
    }

    @Test
    fun `Should pass to chose card step handler when player has BLOCK card with turn different type and color`() {
        val cardPlayed = Card(
            cardType = CardType.BLOCK,
            color = CardColor.RED,
            value = -1,
        )

        val gameContext: GameContext = generateGame(cardPlayed = cardPlayed)
        gameContext.turnColor = CardColor.RED
        gameContext.cardSelectToPlay =
            Card(
                cardType = CardType.PLUS_TWO,
                color = CardColor.GREEN,
                value = -1,
            )

        Assertions.assertEquals(cardPlayed, gameContext.lastCardPlayed())

        val validatePlayerHasCardStepHandler = ValidateCardPlayedStep(mediator, gameContext)
        validatePlayerHasCardStepHandler.execute()

        verify { mediator.notify(validatePlayerHasCardStepHandler, MediatorEvent.CHOSE_CARD) }
    }

    @Test
    fun `Should pass to active special card step handler when player has BLOCK card with turn different color but same type`() {
        val cardPlayed = Card(
            cardType = CardType.BLOCK,
            color = CardColor.RED,
            value = -1,
        )

        val gameContext: GameContext = generateGame(cardPlayed = cardPlayed)
        gameContext.turnColor = CardColor.RED
        gameContext.cardSelectToPlay =
            Card(
                cardType = CardType.BLOCK,
                color = CardColor.GREEN,
                value = -1,
            )

        Assertions.assertEquals(cardPlayed, gameContext.lastCardPlayed())

        val validateCardPlayedStepStepHandler = ValidateCardPlayedStep(mediator, gameContext)
        validateCardPlayedStepStepHandler.execute()

        verify { mediator.notify(validateCardPlayedStepStepHandler, MediatorEvent.ACTIVATE_SPECIAL_CARD_EFFECT) }
    }

}