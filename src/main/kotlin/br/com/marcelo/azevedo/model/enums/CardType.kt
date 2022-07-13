package br.com.marcelo.azevedo.model.enums

enum class CardType(val typePrefix: String) {

    NUMBER("X"),
    BLOCK("B"),
    REVERT("R"),
    JOKER("J"),
    JOKER_PLUS_FOUR("+4"),
    PLUS_TWO("+2")

}