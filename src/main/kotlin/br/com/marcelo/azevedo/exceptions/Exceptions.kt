package br.com.marcelo.azevedo.exceptions

import br.com.marcelo.azevedo.model.Card

class InvalidCardPlayed(card: Card): RuntimeException()
class InvalidCardIndexPlayed(index: Int): RuntimeException()
class GetNewCardAndFisnishTurnException(card: Card): RuntimeException()