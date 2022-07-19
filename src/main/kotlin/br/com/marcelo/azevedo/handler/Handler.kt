package br.com.marcelo.azevedo.handler

import br.com.marcelo.azevedo.model.Game

interface Handler {

    fun getHandlerGame(): Game
    fun execute()

    fun setNext(handlerStrategy: Handler)

}