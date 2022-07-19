package br.com.marcelo.azevedo.mediator

import br.com.marcelo.azevedo.handler.Handler

interface Mediator {

    fun notify(sender: Handler, vararg event: MediatorEvent)

}