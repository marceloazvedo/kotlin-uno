package br.com.marcelo.azevedo.handler.action

import br.com.marcelo.azevedo.handler.StepHandlerTest
import br.com.marcelo.azevedo.mediator.MediatorEvent
import br.com.marcelo.azevedo.model.Card
import br.com.marcelo.azevedo.model.Game
import br.com.marcelo.azevedo.model.enums.CardColor
import br.com.marcelo.azevedo.model.enums.CardType
import br.com.marcelo.azevedo.util.generateGame
import io.mockk.verify
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class UnderEffectVerificationStepHandlerTest : StepHandlerTest() {

    @Test
    fun `Shoud pass to validate if play has card step if last card played is not special type`() {
        val cardPlayed = Card(
            cardType = CardType.NUMBER,
            color = CardColor.BLUE,
            value = 9,
        )

        val game: Game = generateGame(cardPlayed = cardPlayed)

        Assertions.assertEquals(false, game.isSpecialEffectActive)
        Assertions.assertEquals(cardPlayed, game.lastCardPlayed())

        val underEffectVerificationStepHandler = UnderEffectVerificationStepHandler(mediator, game)
        underEffectVerificationStepHandler.execute()

        verify { mediator.notify(underEffectVerificationStepHandler, MediatorEvent.VALIDATE_IF_PLAYER_HAS_CARDS) }
        Assertions.assertEquals(false, game.isSpecialEffectActive)
    }

    @Test
    fun `Shoud pass to validate if play has card step if isSpecialEffectActive and last card is REVERT`() {
        val cardPlayed = Card(
            cardType = CardType.REVERT,
            color = CardColor.BLUE,
            value = -1,
        )

        val game: Game = generateGame(cardPlayed = cardPlayed)
        game.isSpecialEffectActive = true

        Assertions.assertEquals(cardPlayed, game.lastCardPlayed())

        val underEffectVerificationStepHandler = UnderEffectVerificationStepHandler(mediator, game)
        underEffectVerificationStepHandler.execute()

        verify { mediator.notify(underEffectVerificationStepHandler, MediatorEvent.VALIDATE_IF_PLAYER_HAS_CARDS) }
        Assertions.assertEquals(true, game.isSpecialEffectActive)
    }

    @Test
    fun `Shoud pass to validate if play has card step if isSpecialEffectActive and last card is JOKER`() {
        val cardPlayed = Card(
            cardType = CardType.JOKER,
            color = CardColor.NO_COLOR,
            value = -1,
        )

        val game: Game = generateGame(cardPlayed = cardPlayed)
        game.isSpecialEffectActive = true

        Assertions.assertEquals(cardPlayed, game.lastCardPlayed())

        val underEffectVerificationStepHandler = UnderEffectVerificationStepHandler(mediator, game)
        underEffectVerificationStepHandler.execute()

        verify { mediator.notify(underEffectVerificationStepHandler, MediatorEvent.VALIDATE_IF_PLAYER_HAS_CARDS) }
        Assertions.assertEquals(true, game.isSpecialEffectActive)
    }

    @Test
    fun `Shoud pass to validate if play has card step if card type is a REVERT and isSpecialEffectActive equals true`() {
        val cardPlayed = Card(
            cardType = CardType.REVERT,
            color = CardColor.BLUE,
            value = -1,
        )

        val game: Game = generateGame(cardPlayed = cardPlayed)
        game.isSpecialEffectActive = true

        Assertions.assertEquals(cardPlayed, game.lastCardPlayed())

        val underEffectVerificationStepHandler = UnderEffectVerificationStepHandler(mediator, game)
        underEffectVerificationStepHandler.execute()

        verify { mediator.notify(underEffectVerificationStepHandler, MediatorEvent.VALIDATE_IF_PLAYER_HAS_CARDS) }
        Assertions.assertEquals(true, game.isSpecialEffectActive)
    }

    @Test
    fun `Shoud pass to player blocked step if card type is a BLOCK`() {
        val cardPlayed = Card(
            cardType = CardType.BLOCK,
            color = CardColor.BLUE,
            value = -1,
        )

        val game: Game = generateGame(cardPlayed = cardPlayed)
        game.isSpecialEffectActive = true

        Assertions.assertEquals(cardPlayed, game.lastCardPlayed())

        val underEffectVerificationStepHandler = UnderEffectVerificationStepHandler(mediator, game)
        underEffectVerificationStepHandler.execute()

        verify { mediator.notify(underEffectVerificationStepHandler, MediatorEvent.PLAYER_BLOCKED) }
        Assertions.assertEquals(true, game.isSpecialEffectActive)
    }

    @Test
    fun `Shoud pass to make player get cards step if card type is a PLUS_TWO`() {
        val cardPlayed = Card(
            cardType = CardType.PLUS_TWO,
            color = CardColor.BLUE,
            value = -1,
        )

        val game: Game = generateGame(cardPlayed = cardPlayed)
        game.isSpecialEffectActive = true

        Assertions.assertEquals(cardPlayed, game.lastCardPlayed())

        val underEffectVerificationStepHandler = UnderEffectVerificationStepHandler(mediator, game)
        underEffectVerificationStepHandler.execute()

        verify { mediator.notify(underEffectVerificationStepHandler, MediatorEvent.MAKE_PLAYER_GET_CARDS) }
        Assertions.assertEquals(true, game.isSpecialEffectActive)
    }

    @Test
    fun `Shoud pass to make player get cards step if card type is a JOKER_PLUS_FOUR`() {
        val cardPlayed = Card(
            cardType = CardType.JOKER_PLUS_FOUR,
            color = CardColor.BLUE,
            value = -1,
        )

        val game: Game = generateGame(cardPlayed = cardPlayed)
        game.isSpecialEffectActive = true

        Assertions.assertEquals(cardPlayed, game.lastCardPlayed())

        val underEffectVerificationStepHandler = UnderEffectVerificationStepHandler(mediator, game)
        underEffectVerificationStepHandler.execute()

        verify { mediator.notify(underEffectVerificationStepHandler, MediatorEvent.MAKE_PLAYER_GET_CARDS) }
        Assertions.assertEquals(true, game.isSpecialEffectActive)
    }

}