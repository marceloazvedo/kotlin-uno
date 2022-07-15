package br.com.marcelo.azevedo.handler.action

import br.com.marcelo.azevedo.mediator.Mediator
import br.com.marcelo.azevedo.mediator.MediatorEvent
import br.com.marcelo.azevedo.model.Card
import br.com.marcelo.azevedo.model.Game
import br.com.marcelo.azevedo.model.enums.CardColor
import br.com.marcelo.azevedo.model.enums.CardType
import br.com.marcelo.azevedo.util.generateGame
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class GetCardStepHandlerTest {

    private val mediator: Mediator = mockk()

    @BeforeEach
    fun setupTest() {
        every { mediator.notify(any(), any()) } returns Unit
    }

    @Test
    fun `Should make player to get one card`() {
        val cardPlayed = Card(
            cardType = CardType.NUMBER,
            color = CardColor.BLUE,
            value = 9,
        )

        val game: Game = generateGame(cardPlayed = cardPlayed)

        val getCardStepHandler = GetCardStepHandler(mediator, game)
        getCardStepHandler.execute()

        verify { mediator.notify(getCardStepHandler, MediatorEvent.NEXT_TURN) }

    }

    @Test
    fun `Should make player to get two card`() {
        val cardPlayed = Card(
            cardType = CardType.PLUS_TWO,
            color = CardColor.YELLOW,
            value = -1,
        )

        val game: Game = generateGame(cardPlayed = cardPlayed)

        val getCardStepHandler = GetCardStepHandler(mediator, game)
        getCardStepHandler.execute()

        verify { mediator.notify(getCardStepHandler, MediatorEvent.NEXT_TURN) }

    }

    @Test
    fun `Should make player to get four card`() {
        val cardPlayed = Card(
            cardType = CardType.JOKER_PLUS_FOUR,
            color = CardColor.NO_COLOR,
            value = -1,
        )

        val game: Game = generateGame(
            cardPlayed = cardPlayed
        )

        val getCardStepHandler = GetCardStepHandler(mediator, game)
        getCardStepHandler.execute()

        verify { mediator.notify(getCardStepHandler, MediatorEvent.NEXT_TURN) }

    }

}