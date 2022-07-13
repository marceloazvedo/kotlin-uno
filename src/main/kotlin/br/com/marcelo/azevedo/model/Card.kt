package br.com.marcelo.azevedo.model

import br.com.marcelo.azevedo.model.enums.CardColor
import br.com.marcelo.azevedo.model.enums.CardType

data class Card(
    val cardType: CardType,
    val color: CardColor,
    val value: Int,
) {

    fun isSpecial(): Boolean = cardType != CardType.NUMBER

}
