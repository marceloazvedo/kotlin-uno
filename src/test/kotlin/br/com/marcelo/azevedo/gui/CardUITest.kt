package br.com.marcelo.azevedo.gui

import br.com.marcelo.azevedo.model.Card
import br.com.marcelo.azevedo.model.CardColor
import br.com.marcelo.azevedo.model.CardType
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
            _____ _____ 
            | 5b| | 9y| 
            ----- ----- 
        """.trimIndent()
        assertEquals(output, cardsToPrint)
    }

}