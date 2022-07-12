package br.com.marcelo.azevedo.service

import br.com.marcelo.azevedo.model.CardColor
import br.com.marcelo.azevedo.model.CardType
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

const val DECK_SIZE = 108
const val COLOR_CARDS_SIZE = 19
const val JOKER_PLUS_FOUR_CARDS_SIZE = 4
const val JOKER_CARDS_SIZE = 4
const val PLUS_TWO_CARDS_SIZE = 8
const val REVERT_CARDS_SIZE = 8
const val BLOCK_CARDS_SIZE = 8
const val CARD_NUMBER_ZERO_SIZE = 1
const val CARD_NUMBER_ZERO_VALUE = 0

class DeckServiceTest {

    private val deckService = DeckService()

    @Test
    fun `Shoud make a new deck wiith all cards`() {
        val deck = deckService.createDeck()

        assertEquals(DECK_SIZE, deck.cards.size)

        val blueCards = deck.cards
            .filter { card -> card.color == CardColor.BLUE && card.cardType == CardType.NUMBER }
            .sortedBy { it.value }
        val greenCards = deck.cards
            .filter { card -> card.color == CardColor.GREEN && card.cardType == CardType.NUMBER }
            .sortedBy { it.value }
        val redCards = deck.cards
            .filter { card -> card.color == CardColor.RED && card.cardType == CardType.NUMBER }
            .sortedBy { it.value }
        val yellowCards = deck.cards
            .filter { card -> card.color == CardColor.YELLOW && card.cardType == CardType.NUMBER }
            .sortedBy { it.value }

        assertEquals(COLOR_CARDS_SIZE, blueCards.size)
        assertEquals(COLOR_CARDS_SIZE, greenCards.size)
        assertEquals(COLOR_CARDS_SIZE, redCards.size)
        assertEquals(COLOR_CARDS_SIZE, yellowCards.size)

        val jokerPlusFourCardsSize = deck.cards.filter { card -> card.cardType == CardType.JOKER_PLUS_FOUR }.size
        val jokerCardsSize = deck.cards.filter { card -> card.cardType == CardType.JOKER }.size

        assertEquals(JOKER_PLUS_FOUR_CARDS_SIZE, jokerPlusFourCardsSize)
        assertEquals(JOKER_CARDS_SIZE, jokerCardsSize)

        val plusTwoCardsSize = deck.cards.filter { card -> card.cardType == CardType.PLUS_TWO }.size
        val revertCardsSize = deck.cards.filter { card -> card.cardType == CardType.REVERT }.size
        val blockCardsSize = deck.cards.filter { card -> card.cardType == CardType.BLOCK }.size

        assertEquals(PLUS_TWO_CARDS_SIZE, plusTwoCardsSize)
        assertEquals(REVERT_CARDS_SIZE, revertCardsSize)
        assertEquals(BLOCK_CARDS_SIZE, blockCardsSize)

        for (i in 1..9) {
            assertEquals(i, blueCards[(i * 2)].value)
            assertEquals(i, blueCards[(i * 2) - 1].value)

            assertEquals(i, redCards[(i * 2)].value)
            assertEquals(i, redCards[(i * 2) - 1].value)

            assertEquals(i, yellowCards[(i * 2)].value)
            assertEquals(i, yellowCards[(i * 2) - 1].value)

            assertEquals(i, greenCards[(i * 2)].value)
            assertEquals(i, greenCards[(i * 2) - 1].value)
        }

        val cardZeroBlue = blueCards.filter { it.value == 0 }
        val cardZeroRed = redCards.filter { it.value == 0 }
        val cardZeroYellow = yellowCards.filter { it.value == 0 }
        val cardZeroGreen = greenCards.filter { it.value == 0 }

        assertEquals(CARD_NUMBER_ZERO_SIZE, cardZeroBlue.size)
        assertEquals(CARD_NUMBER_ZERO_SIZE, cardZeroRed.size)
        assertEquals(CARD_NUMBER_ZERO_SIZE, cardZeroYellow.size)
        assertEquals(CARD_NUMBER_ZERO_SIZE, cardZeroGreen.size)
        assertEquals(CARD_NUMBER_ZERO_SIZE, cardZeroBlue.first().value)
        assertEquals(CARD_NUMBER_ZERO_SIZE, cardZeroRed.first().value)
        assertEquals(CARD_NUMBER_ZERO_SIZE, cardZeroYellow.first().value)
        assertEquals(CARD_NUMBER_ZERO_SIZE, cardZeroGreen.first().value)

    }

}