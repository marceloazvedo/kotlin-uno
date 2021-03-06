package br.com.marcelo.azevedo.mediator

import br.com.marcelo.azevedo.handler.Handler
import br.com.marcelo.azevedo.handler.action.*
import br.com.marcelo.azevedo.handler.input.ChoseCardStepHandler
import br.com.marcelo.azevedo.handler.input.SelectColorStepHandler
import br.com.marcelo.azevedo.model.GameContext

class GameMediator(gameContext: GameContext) : Mediator {

    private val choseCardStepHandler = ChoseCardStepHandler(this, gameContext)
    private val specialCardEffectStepHandler = SpecialCardEffectStepHandler(this, gameContext)
    private val getCardStepHandler = GetCardStepHandler(this, gameContext)
    private val underEffectVerificationStepHandler = UnderEffectVerificationStepHandler(this, gameContext)
    private val validatePlayerHasCardStepHandler = ValidatePlayerHasCardStepHandler(this, gameContext)
    private val validateCardPlayedStepHandler = ValidateCardPlayedStepHandler(this, gameContext)
    private val selectColorStepHandler = SelectColorStepHandler(this, gameContext)
    private val playerBlockedStepHandler = PlayerBlockedStepHandler(this, gameContext)
    private val revertGameDirectionStepHandler = RevertGameDirectionStepHandler(this, gameContext)
    private val nextTurnStepHandler = NextTurnStepHandler(this, gameContext)
    private val endGameStepHandler = EndGameStepHandler(this, gameContext)

    init {
        notify(underEffectVerificationStepHandler, MediatorEvent.UNDER_EFFECT_VERIFICATION)
    }

    override fun notify(sender: Handler, vararg events: MediatorEvent) {
        events.forEachIndexed { index, event ->
            if (index > 0) {
                underEffectVerificationStepHandler.setNext(getHandler(event))
            }
        }
        getHandler(events.first()).execute()
    }

    private fun getHandler(event: MediatorEvent): Handler {
        return when (event) {
            MediatorEvent.UNDER_EFFECT_VERIFICATION -> underEffectVerificationStepHandler
            MediatorEvent.VALIDATE_IF_PLAYER_HAS_CARDS -> validatePlayerHasCardStepHandler
            MediatorEvent.VALIDATE_CARD_PLAYED -> validateCardPlayedStepHandler
            MediatorEvent.MAKE_PLAYER_GET_CARDS -> getCardStepHandler
            MediatorEvent.CHOSE_CARD -> choseCardStepHandler
            MediatorEvent.SELECT_COLOR_GAME -> selectColorStepHandler
            MediatorEvent.PLAYER_BLOCKED -> playerBlockedStepHandler
            MediatorEvent.REVERT_GAME_DIRECTION -> revertGameDirectionStepHandler
            MediatorEvent.ACTIVATE_SPECIAL_CARD_EFFECT -> specialCardEffectStepHandler
            MediatorEvent.NEXT_TURN -> nextTurnStepHandler
            MediatorEvent.END_GAME -> endGameStepHandler
            else -> getCardStepHandler
        }
    }

}