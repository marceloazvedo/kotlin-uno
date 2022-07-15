package br.com.marcelo.azevedo.util

import br.com.marcelo.azevedo.model.Card
import br.com.marcelo.azevedo.model.Game
import br.com.marcelo.azevedo.model.Player
import br.com.marcelo.azevedo.model.enums.CardColor
import br.com.marcelo.azevedo.model.enums.CardType

fun generateGame(cardPlayed: Card): Game {
    val players = mutableListOf(geneatePlayer(), geneatePlayer(), geneatePlayer(), geneatePlayer())

    return Game(
        playedCards = mutableListOf(cardPlayed),
        turnColor = generateRandomColor(),
        players = players,
        playerInTurn = players.first(),
        remainingCards = mutableListOf(generateRandomCardNumber()),
    )
}

fun geneatePlayer() =
    Player(
        name = "aaa_${(0..10).random()}",
        cards = mutableListOf(
            generateRandomCardNumber(),
            generateJokerRandomCard(),
            generateRandomCardNumber(),
            generateRandomCardNumber(),
            generateRandomSpecialCardWithColor(),
        )
    )

fun generateRandomCardNumber() =
    Card(
        cardType = CardType.NUMBER,
        color = generateRandomColor(),
        value = generateRandomCardValue(),
    )

fun generateJokerRandomCard() =
    Card(
        cardType = generateRandomSpecialCardTypeNoColor(),
        color = CardColor.NO_COLOR,
        value = -1,
    )

fun generateRandomSpecialCardWithColor() =
    Card(
        cardType = generateRandomSpecialCardTypeNoColor(),
        color = CardColor.NO_COLOR,
        value = -1,
    )

fun generateRandomColor() = CardColor.values()[(0 until CardColor.values().size).random()]

fun generateRandomCardType() = CardType.values()[(0 until CardType.values().size).random()]

fun generateRandomCardValue() = (0..9).random()

fun generateRandomSpecialCardTypeWithColor() =
    listOf(CardType.BLOCK, CardType.PLUS_TWO, CardType.REVERT).random()

fun generateRandomSpecialCardTypeNoColor() =
    listOf(CardType.JOKER_PLUS_FOUR, CardType.JOKER).random()

