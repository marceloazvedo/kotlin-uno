package br.com.marcelo.azevedo.mediator

import br.com.marcelo.azevedo.handler.Step
import br.com.marcelo.azevedo.handler.action.*
import br.com.marcelo.azevedo.handler.input.ChoseCardStepHandler
import br.com.marcelo.azevedo.handler.input.SelectColorStepHandler
import br.com.marcelo.azevedo.model.GameContext

class GameMediator(gameContext: GameContext) : Mediator {

    private val choseCardStepHandler = ChoseCardStepHandler(this, gameContext)
    private val specialCardEffectStep = SpecialCardEffectStep(this, gameContext)
    private val getCardStep = GetCardStep(this, gameContext)
    private val underEffectVerificationStep = UnderEffectVerificationStep(this, gameContext)
    private val validatePlayerHasCardStep = ValidatePlayerHasCardStep(this, gameContext)
    private val validateCardPlayedStep = ValidateCardPlayedStep(this, gameContext)
    private val selectColorStepHandler = SelectColorStepHandler(this, gameContext)
    private val playerBlockedStep = PlayerBlockedStep(this, gameContext)
    private val revertGameDirectionStep = RevertGameDirectionStep(this, gameContext)
    private val nextTurnStep = NextTurnStep(this, gameContext)
    private val endGameStep = EndGameStep(this, gameContext)

    init {
        notify(MediatorEvent.UNDER_EFFECT_VERIFICATION)
    }

    override fun notify(vararg nextEvent: MediatorEvent) {
        nextEvent.forEachIndexed { index, event ->
            if (index > 0) {
                underEffectVerificationStep.setNext(getHandler(event))
            }
        }
        getHandler(nextEvent.first()).execute()
    }

    private fun getHandler(event: MediatorEvent): Step {
        return when (event) {
            MediatorEvent.UNDER_EFFECT_VERIFICATION -> underEffectVerificationStep
            MediatorEvent.VALIDATE_IF_PLAYER_HAS_CARDS -> validatePlayerHasCardStep
            MediatorEvent.VALIDATE_CARD_PLAYED -> validateCardPlayedStep
            MediatorEvent.MAKE_PLAYER_GET_CARDS -> getCardStep
            MediatorEvent.CHOSE_CARD -> choseCardStepHandler
            MediatorEvent.SELECT_COLOR_GAME -> selectColorStepHandler
            MediatorEvent.PLAYER_BLOCKED -> playerBlockedStep
            MediatorEvent.REVERT_GAME_DIRECTION -> revertGameDirectionStep
            MediatorEvent.ACTIVATE_SPECIAL_CARD_EFFECT -> specialCardEffectStep
            MediatorEvent.NEXT_TURN -> nextTurnStep
            MediatorEvent.END_GAME -> endGameStep
            else -> getCardStep
        }
    }

}