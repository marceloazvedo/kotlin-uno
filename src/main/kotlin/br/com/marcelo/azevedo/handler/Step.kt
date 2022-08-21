package br.com.marcelo.azevedo.handler

import br.com.marcelo.azevedo.model.GameContext

interface Step {

    fun getHandlerGame(): GameContext
    fun execute()

    fun setNext(stepStrategy: Step)

}