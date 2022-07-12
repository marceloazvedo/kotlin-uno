package br.com.marcelo.azevedo

import br.com.marcelo.azevedo.gui.GameUI
import br.com.marcelo.azevedo.service.DeckService
import br.com.marcelo.azevedo.service.PlayerService

fun main() {
    println(GameUI(playerService = PlayerService(), deckService = DeckService()).start())
}
