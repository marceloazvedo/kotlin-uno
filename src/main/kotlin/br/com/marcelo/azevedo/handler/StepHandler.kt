package br.com.marcelo.azevedo.handler

import br.com.marcelo.azevedo.mediator.Mediator
import br.com.marcelo.azevedo.mediator.MediatorEvent
import br.com.marcelo.azevedo.model.Game

abstract class StepHandler(
    private val game: Game,
    private val mediator: Mediator
) : Handler {

    protected val toExecute = mutableListOf<Handler>()

    abstract override fun execute()
    override fun getHandlerGame(): Game = this.game

    override fun setNext(handlerStrategy: Handler) {
        toExecute.add(handlerStrategy)
    }

    fun executeNextAndNotify(event: MediatorEvent) {
        if (toExecute.isNotEmpty()) {
            val handlerToExecute = toExecute.first()
            handlerToExecute.execute()
            toExecute.removeAt(0)
        }
        mediator.notify(this, event)
    }

}