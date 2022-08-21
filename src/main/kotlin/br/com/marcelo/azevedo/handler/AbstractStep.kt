package br.com.marcelo.azevedo.handler

import br.com.marcelo.azevedo.mediator.Mediator
import br.com.marcelo.azevedo.mediator.MediatorEvent
import br.com.marcelo.azevedo.model.GameContext

abstract class AbstractStep(
    private val gameContext: GameContext,
    private val mediator: Mediator
) : Step {

    private val toExecute = mutableListOf<Step>()

    abstract override fun execute()
    override fun getHandlerGame(): GameContext = this.gameContext

    override fun setNext(stepStrategy: Step) {
        toExecute.add(stepStrategy)
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