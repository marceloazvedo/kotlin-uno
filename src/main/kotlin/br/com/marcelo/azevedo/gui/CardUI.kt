package br.com.marcelo.azevedo.gui

import br.com.marcelo.azevedo.model.Card
import br.com.marcelo.azevedo.model.CardType

class CardUI {

    fun printCards(cards: List<Card>): String {

        val cardsOutputTop = cards.map { cardDelimiterTop() }.reduce { acc, s -> acc + s }
        val cardsOutputContent = cards.map { card -> cardContent(card) }.reduce { acc, s -> acc + s }
        val cardsOutputBottom = cards.map { cardDelimiterBottom() }.reduce { acc, s -> acc + s }


        return "${cardsOutputTop}\n${cardsOutputContent}\n${cardsOutputBottom}"
    }

    private fun cardDelimiterTop(): String = "_____ "

    private fun cardDelimiterBottom(): String = "----- "

    private fun cardContent(card: Card): String {

        val content = when (card.cardType) {
            CardType.NUMBER -> card.value
            else -> card.cardType.typePrefix
        }

        val complement = when (card.cardType) {
            CardType.JOKER_PLUS_FOUR, CardType.PLUS_TWO -> ""
            else -> " "
        }
        return "|%s%s%s| ".format(complement, content, card.color.colorPrefix)
    }

}