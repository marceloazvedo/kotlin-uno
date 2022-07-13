package br.com.marcelo.azevedo.service

import br.com.marcelo.azevedo.exceptions.GetNewCardAndFisnishTurnException
import br.com.marcelo.azevedo.exceptions.InvalidCardIndexPlayed
import br.com.marcelo.azevedo.exceptions.InvalidCardPlayed
import br.com.marcelo.azevedo.model.Card
import br.com.marcelo.azevedo.model.Deck
import br.com.marcelo.azevedo.model.Game
import br.com.marcelo.azevedo.model.Player
import br.com.marcelo.azevedo.model.enums.CardType
import br.com.marcelo.azevedo.model.enums.GameDirection

class GameService {

    fun createGame(players: List<Player>, deck: Deck): Game {
        val firstGameCard = deck.cards.first()
        return Game(
            playerInTurn = players.first(),
            players = players,
            remainingCards = deck.cards.drop(1).toMutableList(),
            playedCards = mutableListOf(firstGameCard)
        )
    }

    fun playCard(game: Game, cardIndexPlayed: Int): Game {
        val previousCard = game.cardOfTurn()
        val cardPlayed =
            try {
                game.playerInTurn.cards[cardIndexPlayed]
            } catch (_: IndexOutOfBoundsException) {
                throw InvalidCardIndexPlayed(cardIndexPlayed)
            }
        validateCardPlayed(previousCard, cardPlayed)

        if(cardPlayed.isSpecial()) activeSpecialEffect(game, cardPlayed)

        game.playerInTurn.cards.remove(cardPlayed)
        game.playedCards.add(cardPlayed)

        passTurnNextPlayer(game)
        return game
    }

    private fun validateCardPlayed(previousCard: Card, cardPlayed: Card) {
        if (cardPlayed.cardType == CardType.NUMBER && !(previousCard.color == cardPlayed.color || previousCard.value == cardPlayed.value)) {
            throw InvalidCardPlayed(cardPlayed)
        }
        if (cardPlayed.cardType == CardType.BLOCK && previousCard.color != cardPlayed.color) {
            throw InvalidCardPlayed(cardPlayed)
        }
        if (cardPlayed.cardType == CardType.REVERT && previousCard.color != cardPlayed.color) {
            throw InvalidCardPlayed(cardPlayed)
        }
        if (cardPlayed.cardType == CardType.PLUS_TWO && previousCard.color != cardPlayed.color) {
            throw InvalidCardPlayed(cardPlayed)
        }
    }

    private fun getNewCardAndFinishTurn(game: Game) {
        val card = game.remainingCards.first()
        game.remainingCards = game.remainingCards.drop(1).toMutableList()
        game.playerInTurn.cards += card
        passTurnNextPlayer(game)
        throw GetNewCardAndFisnishTurnException(card)
    }

    fun hasCardToPlay(game: Game) {
        val previousCard = game.cardOfTurn()
        val noHasCard: Boolean = game.playerInTurn.cards.none {
            val isValidCard = try {
                validateCardPlayed(previousCard, it)
                true
            } catch (_: Exception) {
                false
            }
            isValidCard
        }
        if (noHasCard) getNewCardAndFinishTurn(game)
    }

    private fun passTurnNextPlayer(game: Game) {
        val playerIndex = game.players.indexOf(game.playerInTurn)
        val nextIndex = if (game.direction == GameDirection.FORWARD) {
            playerIndex.inc()
        } else {
            playerIndex.dec()
        }.let { index ->
            if (index < 0) game.players.size - 1
            else if (index >= game.players.size) 0
            else index
        }
        val nextPlayer = game.players[nextIndex]
        game.playerInTurn = nextPlayer
    }

    private fun activeSpecialEffect(game: Game, cardPlayed: Card) {
        
    }

}