package br.com.marcelo.azevedo.service

import br.com.marcelo.azevedo.model.Card
import br.com.marcelo.azevedo.model.enums.CardColor
import br.com.marcelo.azevedo.model.enums.CardType
import br.com.marcelo.azevedo.model.Deck
import br.com.marcelo.azevedo.util.CARD_INVALID_VALUE
import br.com.marcelo.azevedo.util.CARD_JOKER_SIZE
import br.com.marcelo.azevedo.util.CARD_NUMBER_ZERO_VALUE

class DeckService {

    fun createDeck(): Deck {
        val cards = mutableListOf<Card>()

        for (i in 1..9) {
            cards.add(Card(cardType = CardType.NUMBER, color = CardColor.BLUE, value = i))
            cards.add(Card(cardType = CardType.NUMBER, color = CardColor.BLUE, value = i))

            cards.add(Card(cardType = CardType.NUMBER, color = CardColor.RED, value = i))
            cards.add(Card(cardType = CardType.NUMBER, color = CardColor.RED, value = i))

            cards.add(Card(cardType = CardType.NUMBER, color = CardColor.YELLOW, value = i))
            cards.add(Card(cardType = CardType.NUMBER, color = CardColor.YELLOW, value = i))

            cards.add(Card(cardType = CardType.NUMBER, color = CardColor.GREEN, value = i))
            cards.add(Card(cardType = CardType.NUMBER, color = CardColor.GREEN, value = i))
        }

        cards.add(Card(cardType = CardType.NUMBER, color = CardColor.BLUE, value = CARD_NUMBER_ZERO_VALUE))
        cards.add(Card(cardType = CardType.NUMBER, color = CardColor.RED, value = CARD_NUMBER_ZERO_VALUE))
        cards.add(Card(cardType = CardType.NUMBER, color = CardColor.YELLOW, value = CARD_NUMBER_ZERO_VALUE))
        cards.add(Card(cardType = CardType.NUMBER, color = CardColor.GREEN, value = CARD_NUMBER_ZERO_VALUE))


        for (i in 1..CARD_JOKER_SIZE) {
            cards.add(Card(cardType = CardType.JOKER, color = CardColor.NO_COLOR, value = CARD_INVALID_VALUE))
            cards.add(Card(cardType = CardType.JOKER_PLUS_FOUR, color = CardColor.NO_COLOR, value = CARD_INVALID_VALUE))
        }

        for (i in 1..2) {
            cards.add(Card(cardType = CardType.PLUS_TWO, color = CardColor.BLUE, value = CARD_INVALID_VALUE))
            cards.add(Card(cardType = CardType.REVERT, color = CardColor.BLUE, value = CARD_INVALID_VALUE))
            cards.add(Card(cardType = CardType.BLOCK, color = CardColor.BLUE, value = CARD_INVALID_VALUE))

            cards.add(Card(cardType = CardType.PLUS_TWO, color = CardColor.RED, value = CARD_INVALID_VALUE))
            cards.add(Card(cardType = CardType.REVERT, color = CardColor.RED, value = CARD_INVALID_VALUE))
            cards.add(Card(cardType = CardType.BLOCK, color = CardColor.RED, value = CARD_INVALID_VALUE))

            cards.add(Card(cardType = CardType.PLUS_TWO, color = CardColor.YELLOW, value = CARD_INVALID_VALUE))
            cards.add(Card(cardType = CardType.REVERT, color = CardColor.YELLOW, value = CARD_INVALID_VALUE))
            cards.add(Card(cardType = CardType.BLOCK, color = CardColor.YELLOW, value = CARD_INVALID_VALUE))

            cards.add(Card(cardType = CardType.PLUS_TWO, color = CardColor.GREEN, value = CARD_INVALID_VALUE))
            cards.add(Card(cardType = CardType.REVERT, color = CardColor.GREEN, value = CARD_INVALID_VALUE))
            cards.add(Card(cardType = CardType.BLOCK, color = CardColor.GREEN, value = CARD_INVALID_VALUE))
        }

        return Deck(
            cards = cards.shuffled().toMutableList()
        )
    }

}