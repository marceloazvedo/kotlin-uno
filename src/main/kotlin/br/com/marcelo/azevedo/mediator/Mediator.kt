package br.com.marcelo.azevedo.mediator

import br.com.marcelo.azevedo.handler.HandlerStrategy

interface Mediator {

    fun notify(sender: HandlerStrategy, event: MediatorEvent)

}