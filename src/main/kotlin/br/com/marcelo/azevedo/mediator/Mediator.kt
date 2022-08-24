package br.com.marcelo.azevedo.mediator

interface Mediator {

    fun notify(vararg nextEvent: MediatorEvent)

}