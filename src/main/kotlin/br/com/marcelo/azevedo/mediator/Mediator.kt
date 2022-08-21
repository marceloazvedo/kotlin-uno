package br.com.marcelo.azevedo.mediator

import br.com.marcelo.azevedo.handler.Step

interface Mediator {

    fun notify(sender: Step, vararg nextEvent: MediatorEvent)

}