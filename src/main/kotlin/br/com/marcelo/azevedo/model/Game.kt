package br.com.marcelo.azevedo.model

import br.com.marcelo.azevedo.model.enums.CardColor
import br.com.marcelo.azevedo.model.enums.GameDirection

data class Game(
    var direction: GameDirection = GameDirection.FORWARD,
    var remainingCards: MutableList<Card>,
    var playedCards: MutableList<Card>,
    val players: List<Player>,
    var playerInTurn: Player,
    var turnColor: CardColor,
    var isSpecialEffectActive: Boolean = false,
    var cardSelectToPlay: Card? = null,
) {

    fun lastCardPlayed(): Card = playedCards.last()

}
