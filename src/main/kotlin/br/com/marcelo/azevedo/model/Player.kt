package br.com.marcelo.azevedo.model

data class Player(
    val name: String,
    var cards: MutableList<Card>,
)
