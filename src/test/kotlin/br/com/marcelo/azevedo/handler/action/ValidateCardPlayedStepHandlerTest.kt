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

class ValidateCardPlayedStepHandlerTest : StepHandlerTest() {

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

        val underEffectVerificationStepHandler = ValidateCardPlayedStepHandler(mediator, gameContext)
        underEffectVerificationStepHandler.execute()

        verify { mediator.notify(underEffectVerificationStepHandler, MediatorEvent.CHOSE_CARD) }
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

        val underEffectVerificationStepHandler = ValidateCardPlayedStepHandler(mediator, gameContext)
        underEffectVerificationStepHandler.execute()

        verify { mediator.notify(underEffectVerificationStepHandler, MediatorEvent.ACTIVATE_SPECIAL_CARD_EFFECT) }
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

        val underEffectVerificationStepHandler = ValidateCardPlayedStepHandler(mediator, gameContext)
        underEffectVerificationStepHandler.execute()

        verify { mediator.notify(underEffectVerificationStepHandler, MediatorEvent.CHOSE_CARD) }
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

        val underEffectVerificationStepHandler = ValidateCardPlayedStepHandler(mediator, gameContext)
        underEffectVerificationStepHandler.execute()

        verify { mediator.notify(underEffectVerificationStepHandler, MediatorEvent.NEXT_TURN) }
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

        val underEffectVerificationStepHandler = ValidateCardPlayedStepHandler(mediator, gameContext)
        underEffectVerificationStepHandler.execute()

        verify { mediator.notify(underEffectVerificationStepHandler, MediatorEvent.NEXT_TURN) }
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

        val underEffectVerificationStepHandler = ValidateCardPlayedStepHandler(mediator, gameContext)
        underEffectVerificationStepHandler.execute()

        verify { mediator.notify(underEffectVerificationStepHandler, MediatorEvent.CHOSE_CARD) }
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

        val underEffectVerificationStepHandler = ValidateCardPlayedStepHandler(mediator, gameContext)
        underEffectVerificationStepHandler.execute()

        verify { mediator.notify(underEffectVerificationStepHandler, MediatorEvent.CHOSE_CARD) }
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

        val underEffectVerificationStepHandler = ValidateCardPlayedStepHandler(mediator, gameContext)
        underEffectVerificationStepHandler.execute()

        verify { mediator.notify(underEffectVerificationStepHandler, MediatorEvent.ACTIVATE_SPECIAL_CARD_EFFECT) }
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

        val underEffectVerificationStepHandler = ValidateCardPlayedStepHandler(mediator, gameContext)
        underEffectVerificationStepHandler.execute()

        verify { mediator.notify(underEffectVerificationStepHandler, MediatorEvent.END_GAME) }
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

        val underEffectVerificationStepHandler = ValidateCardPlayedStepHandler(mediator, gameContext)
        underEffectVerificationStepHandler.execute()

        verify { mediator.notify(underEffectVerificationStepHandler, MediatorEvent.CHOSE_CARD) }
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

        val underEffectVerificationStepHandler = ValidateCardPlayedStepHandler(mediator, gameContext)
        underEffectVerificationStepHandler.execute()

        verify { mediator.notify(underEffectVerificationStepHandler, MediatorEvent.CHOSE_CARD) }
    }

}