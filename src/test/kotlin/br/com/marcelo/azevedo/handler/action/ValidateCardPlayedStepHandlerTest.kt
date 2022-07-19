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

        verify { mediator.notify(underEffectVerificationStepHandler, MediatorEvent.CHOSE_CARD) }
    }

}