package br.com.marcelo.azevedo.ui

import br.com.marcelo.azevedo.model.Card
import br.com.marcelo.azevedo.model.enums.CardColor
import br.com.marcelo.azevedo.model.enums.CardType
import br.com.marcelo.azevedo.util.CARD_INVALID_VALUE
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class CardUITest {

    val cardUI = CardUI()

    @Test
    fun `Shoud print a card type number with color blue and value 2`() {
        val card = Card(
            cardType = CardType.NUMBER,
            color = CardColor.BLUE,
            value = 2,
        )

        val cardsToPrint = cardUI.printCards(listOf(card))


        val output = """
            |001| 
            _____ 
            | 2b| 
            ----- 
        """.trimIndent()
        assertEquals(output, cardsToPrint)
    }

    @Test
    fun `Shoud print a card type joker plus four with no color`() {
        val card = Card(
            cardType = CardType.JOKER_PLUS_FOUR,
            color = CardColor.NO_COLOR,
            value = CARD_INVALID_VALUE,
        )

        val cardsToPrint = cardUI.printCards(listOf(card))


        val output = """
            |001| 
            _____ 
            |+4x| 
            ----- 
        """.trimIndent()
        assertEquals(output, cardsToPrint)
    }

    @Test
    fun `Shoud print cards blue and yellow type number with value 5 and 9s`() {
        val cardOne = Card(
            cardType = CardType.NUMBER,
            color = CardColor.BLUE,
            value = 5,
        )

        val cardTwo = Card(
            cardType = CardType.NUMBER,
            color = CardColor.YELLOW,
            value = 9,
        )

        val cardsToPrint = cardUI.printCards(listOf(cardOne, cardTwo))


        val output = """
            |001| |002| 
            _____ _____ 
            | 5b| | 9y| 
            ----- ----- 
        """.trimIndent()
        assertEquals(output, cardsToPrint)
    }

    @Test
    fun `Shoud print cards joker plus four and yellow plus two`() {
        val cardOne = Card(
            cardType = CardType.JOKER_PLUS_FOUR,
            color = CardColor.NO_COLOR,
            value = CARD_INVALID_VALUE,
        )

        val cardTwo = Card(
            cardType = CardType.PLUS_TWO,
            color = CardColor.YELLOW,
            value = CARD_INVALID_VALUE,
        )

        val cardsToPrint = cardUI.printCards(listOf(cardOne, cardTwo))


        val output = """
            |001| |002| 
            _____ _____ 
            |+4x| |+2y| 
            ----- ----- 
        """.trimIndent()
        assertEquals(output, cardsToPrint)
    }

    @Test
    fun `Shoud print cards joker plus four, number 9 blue, joker and number 5 red`() {
        val cardOne = Card(
            cardType = CardType.JOKER_PLUS_FOUR,
            color = CardColor.NO_COLOR,
            value = CARD_INVALID_VALUE,
        )

        val cardTwo = Card(
            cardType = CardType.NUMBER,
            color = CardColor.BLUE,
            value = 9,
        )

        val cardTree = Card(
            cardType = CardType.JOKER,
            color = CardColor.NO_COLOR,
            value = CARD_INVALID_VALUE,
        )

        val cardFour = Card(
            cardType = CardType.NUMBER,
            color = CardColor.RED,
            value = 5,
        )

        val cardsToPrint = cardUI.printCards(listOf(cardOne, cardTwo, cardTree, cardFour))


        val output = """
            |001| |002| |003| |004| 
            _____ _____ _____ _____ 
            |+4x| | 9b| | Jx| | 5r| 
            ----- ----- ----- ----- 
        """.trimIndent()
        assertEquals(output, cardsToPrint)
    }
}