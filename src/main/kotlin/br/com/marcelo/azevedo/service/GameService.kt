package br.com.marcelo.azevedo.service

import br.com.marcelo.azevedo.model.Deck
import br.com.marcelo.azevedo.model.GameContext
import br.com.marcelo.azevedo.model.Player

class GameService {

    fun createGame(players: List<Player>, deck: Deck): GameContext {
        val firstGameCard = deck.cards.first()
        return GameContext(
            playerInTurn = players.first(),
            players = players,
            remainingCards = deck.cards.drop(1).toMutableList(),
            playedCards = mutableListOf(firstGameCard),
            turnColor = firstGameCard.color,
        )
    }

}