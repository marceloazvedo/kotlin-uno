package br.com.marcelo.azevedo.handler

import br.com.marcelo.azevedo.model.GameContext

interface Handler {

    fun getHandlerGame(): GameContext
    fun execute()

    fun setNext(handlerStrategy: Handler)

}