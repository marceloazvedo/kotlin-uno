package br.com.marcelo.azevedo.gui

import br.com.marcelo.azevedo.model.Deck
import br.com.marcelo.azevedo.model.Player
import br.com.marcelo.azevedo.service.DeckService
import br.com.marcelo.azevedo.service.PlayerService
import br.com.marcelo.azevedo.util.QUIT_PLAYER_SELECTION

class GameUI(
    private val playerService: PlayerService,
    private val deckService: DeckService,
) {

    private val cardUI = CardUI()

    private fun getPlayersName(): List<String> {

        val playersName = mutableListOf<String>()

        println(
            "Ok, let start a game! First of all, we need to know who will play with us!\n" +
                    "PS.: To quit the insert player section, put \"quit\" word."
        )
        var playerNumber = 1
        var playerName: String
        do {
            playerName = getPlayerName(playerNumber)
            playerNumber += 1
            if (playerName != QUIT_PLAYER_SELECTION) {
                playersName += playerName
            } else if (playersName.size <= 1) {
                val playersNeeded = 2 - playersName.size
                println("We need a minimal of two players to create a game. Please, insert more ${if (playersNeeded == 1) "one" else "two"} player${if (playersNeeded == 2) "s" else ""}!")
                playerName = ""
                playerNumber = playersName.size + 1
            }
        } while (playerName != QUIT_PLAYER_SELECTION)

        return playersName
    }

    private fun startRound(players: List<Player>, deck: Deck) {
        println(deck.cards.size)
        println("Ok, let's start our game!")
        var count = 0

        do {
            val playerInTurn = players[count]
            println("Is turn of ${playerInTurn.name}")
            println(cardUI.printCards(playerInTurn.cards))
            val input = readln()
            count++
            if (count >= players.size) count = 0
        } while (true)

    }

    fun start() {
        val playersName = getPlayersName()
        val deck = deckService.createDeck()
        val players = playerService.createPlayers(playersName, deck)
        startRound(players, deck)
    }

    private fun getPlayerName(playerNumber: Int): String {
        println("Insert a name for the Player $playerNumber: ")
        return readln()
    }

}