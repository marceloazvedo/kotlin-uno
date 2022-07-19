package br.com.marcelo.azevedo.service

import br.com.marcelo.azevedo.exceptions.GetNewCardAndFisnishTurnException
import br.com.marcelo.azevedo.exceptions.InvalidCardIndexPlayed
import br.com.marcelo.azevedo.exceptions.InvalidCardPlayed
import br.com.marcelo.azevedo.model.Card
import br.com.marcelo.azevedo.model.Deck
import br.com.marcelo.azevedo.model.GameContext
import br.com.marcelo.azevedo.model.Player
import br.com.marcelo.azevedo.model.enums.CardType
import br.com.marcelo.azevedo.model.enums.GameDirection

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

    fun playCard(gameContext: GameContext, cardIndexPlayed: Int): GameContext {
        val previousCard = gameContext.lastCardPlayed()
        val cardPlayed =
            try {
                gameContext.playerInTurn.cards[cardIndexPlayed]
            } catch (_: IndexOutOfBoundsException) {
                throw InvalidCardIndexPlayed(cardIndexPlayed)
            }
        validateCardToPlaay(previousCard, cardPlayed)

        gameContext.turnColor = cardPlayed.color

        if(cardPlayed.isSpecial()) activeSpecialEffect(gameContext, cardPlayed)

        gameContext.playerInTurn.cards.remove(cardPlayed)
        gameContext.playedCards.add(cardPlayed)

        passTurnNextPlayer(gameContext)
        return gameContext
    }

    private fun validateCardToPlaay(previousCard: Card, cardPlayed: Card) {
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

    private fun getNewCardAndFinishTurn(gameContext: GameContext) {
        val card = gameContext.remainingCards.first()
        gameContext.remainingCards = gameContext.remainingCards.drop(1).toMutableList()
        gameContext.playerInTurn.cards += card
        passTurnNextPlayer(gameContext)
        throw GetNewCardAndFisnishTurnException(card)
    }

    fun hasCardToPlay(gameContext: GameContext) {
        val previousCard = gameContext.lastCardPlayed()
        val noHasCard: Boolean = gameContext.playerInTurn.cards.none {
            val isValidCard = try {
                validateCardToPlaay(previousCard, it)
                true
            } catch (_: Exception) {
                false
            }
            isValidCard
        }
        if (noHasCard) getNewCardAndFinishTurn(gameContext)
    }

    private fun passTurnNextPlayer(gameContext: GameContext) {
        val playerIndex = gameContext.players.indexOf(gameContext.playerInTurn)
        val nextIndex = if (gameContext.direction == GameDirection.FORWARD) {
            playerIndex.inc()
        } else {
            playerIndex.dec()
        }.let { index ->
            if (index < 0) gameContext.players.size - 1
            else if (index >= gameContext.players.size) 0
            else index
        }
        val nextPlayer = gameContext.players[nextIndex]
        gameContext.playerInTurn = nextPlayer
    }

    private fun activeSpecialEffect(gameContext: GameContext, cardPlayed: Card) {

    }

}