package br.com.marcelo.azevedo.handler.action

import br.com.marcelo.azevedo.handler.StepHandlerTest
import br.com.marcelo.azevedo.mediator.MediatorEvent
import br.com.marcelo.azevedo.model.Card
import br.com.marcelo.azevedo.model.GameContext
import br.com.marcelo.azevedo.model.enums.CardColor
import br.com.marcelo.azevedo.model.enums.CardType
import br.com.marcelo.azevedo.util.generateGame
import br.com.marcelo.azevedo.util.generateRandomCardNumber
import io.mockk.verify
import org.junit.jupiter.api.Test

class GetCardStepTest: StepHandlerTest() {

    @Test
    fun `Should make player to get one card`() {
        val cardPlayed = generateRandomCardNumber()

        val gameContext: GameContext = generateGame(cardPlayed = cardPlayed)

        val getCardStep = GetCardStep(mediator, gameContext)
        getCardStep.execute()

        verify { mediator.notify(getCardStep, MediatorEvent.NEXT_TURN) }
    }

    @Test
    fun `Should make player to get two card`() {
        val cardPlayed = Card(
            cardType = CardType.PLUS_TWO,
            color = CardColor.YELLOW,
            value = -1,
        )

        val gameContext: GameContext = generateGame(cardPlayed = cardPlayed)

        val getCardStep = GetCardStep(mediator, gameContext)
        getCardStep.execute()

        verify { mediator.notify(getCardStep, MediatorEvent.NEXT_TURN) }
    }

    @Test
    fun `Should make player to get four card`() {
        val cardPlayed = Card(
            cardType = CardType.JOKER_PLUS_FOUR,
            color = CardColor.NO_COLOR,
            value = -1,
        )

        val gameContext: GameContext = generateGame(
            cardPlayed = cardPlayed
        )

        val getCardStep = GetCardStep(mediator, gameContext)
        getCardStep.execute()

        verify { mediator.notify(getCardStep, MediatorEvent.NEXT_TURN) }
    }

}