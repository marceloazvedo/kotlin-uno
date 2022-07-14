package br.com.marcelo.azevedo.handler

import br.com.marcelo.azevedo.model.Game

interface HandlerStrategy {

    fun getHandlerGame(): Game
    fun execute()

}