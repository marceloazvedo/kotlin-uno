package br.com.marcelo.azevedo.model

import br.com.marcelo.azevedo.model.enums.CardColor
import br.com.marcelo.azevedo.model.enums.GameDirection

data class Game(
    val direction: GameDirection = GameDirection.FORWARD,
    var remainingCards: MutableList<Card>,
    var playedCards: MutableList<Card>,
    val players: List<Player>,
    var playerInTurn: Player,
    val turnColor: CardColor,
) {
    fun cardOfTurn(): Card = playedCards.last()
}
