package br.com.marcelo.azevedo.service

import br.com.marcelo.azevedo.model.Deck
import br.com.marcelo.azevedo.model.Player
import br.com.marcelo.azevedo.util.QUANTITY_CARDS_PLAYERS_START

class PlayerService {

    fun createPlayers(playersName: List<String>, deck: Deck): List<Player> {
        return playersName.map {
            val playerCards = deck.cards.take(QUANTITY_CARDS_PLAYERS_START)
            deck.cards = deck.cards.drop(QUANTITY_CARDS_PLAYERS_START).toMutableList()
            Player(
                name = it,
                cards = playerCards.toMutableList(),
            )
        }
    }

}