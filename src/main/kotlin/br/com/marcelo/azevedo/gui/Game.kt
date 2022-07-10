package br.com.marcelo.azevedo.gui

import br.com.marcelo.azevedo.model.Player

private const val QUIT_PLAYER_SELECTION = "quit"

class Game {


    fun getPlayers(): Array<Player> {

        var players: Array<Player> = arrayOf()

        println(
            "Ok, let start a game! First of all, we need to know who will play with us!\n" +
                    "PS.: To quit the insert player section, put \"quit\" word."
        )
        var playerNumber = 1
        var playerName: String
        do {
            playerName = getPlayerName(playerNumber)
            playerNumber += 1
            if(playerName != QUIT_PLAYER_SELECTION) {
                players += createPlayer(playerName)
            }
        } while (playerName != QUIT_PLAYER_SELECTION)

        return players
    }

    private fun getPlayerName(playerNumber: Int): String {
        println("Insert a nome for the Player $playerNumber: ")
        return readln()
    }

    private fun createPlayer(playerName: String): Player {
        return Player(
            name = playerName,
            cards = listOf()
        )
    }


}