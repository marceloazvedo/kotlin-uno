package br.com.marcelo.azevedo.gui

import br.com.marcelo.azevedo.model.Deck
import br.com.marcelo.azevedo.model.Player
import br.com.marcelo.azevedo.service.DeckService
import br.com.marcelo.azevedo.service.PlayerService
import br.com.marcelo.azevedo.util.QUIT_PLAYER_SELECTION

class GameUI {

    private val cardUI = CardUI()
    private val playerService = PlayerService()
    private val deckService = DeckService()

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
        println("Ok, let's start our game!")
        var count = 0

        do {
            val playerInTurn = players[count]
            println("""
                
                
                
                
                
                
                
                
                
                
                
                Is turn of ${playerInTurn.name}
            """.trimIndent())
            println(cardUI.printCards(playerInTurn.cards))
            print("Please, select your card ${playerInTurn.name}: ")
            try {
                val cardIndex = readln().toInt() -1
                val card = playerInTurn.cards[cardIndex]
                playerInTurn.cards.removeAt(cardIndex)
                count++
                if (count >= players.size) count = 0
            } catch (_: Exception) {
                println("""
                    
                    
                    
                    
                    
                    
                    ## WARNING ##
                    Plase, select a valid index for your card.
                    #############
                """.trimIndent())
            }
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