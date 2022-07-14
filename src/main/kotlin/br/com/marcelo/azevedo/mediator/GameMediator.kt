package br.com.marcelo.azevedo.mediator

import br.com.marcelo.azevedo.handler.HandlerStrategy
import br.com.marcelo.azevedo.handler.action.*
import br.com.marcelo.azevedo.handler.input.ChoseCardStepHandler
import br.com.marcelo.azevedo.handler.input.SelectColorStepHandler
import br.com.marcelo.azevedo.model.Game

class GameMediator(game: Game) : Mediator {

    private val choseCardStepHandler = ChoseCardStepHandler(this, game)
    private val specialCardEffectStepHandler = SpecialCardEffectStepHandler(this, game)
    private val getCardStepHandler = GetCardStepHandler(this, game)
    private val underEffectVerificationStepHandler = UnderEffectVerificationStepHandler(this, game)
    private val validatePlayerHasCardStepHandler = ValidatePlayerHasCardStepHandler(this, game)
    private val validateCardPlayedStepHandler = ValidateCardPlayedStepHandler(this, game)
    private val selectColorStepHandler = SelectColorStepHandler(this, game)
    private val playerBlockedStepHandler = PlayerBlockedStepHandler(this, game)
    private val revertGameDirectionStepHandler = RevertGameDirectionStepHandler(this, game)
    private val nextTurnStepHandler = NextTurnStepHandler(this, game)

    init {
        notify(underEffectVerificationStepHandler, MediatorEvent.UNDER_EFFECT_VERIFICATION)
    }

    override fun notify(sender: HandlerStrategy, event: MediatorEvent) {
        when (event) {
            MediatorEvent.UNDER_EFFECT_VERIFICATION -> underEffectVerificationStepHandler.execute()
            MediatorEvent.VALIDATE_IF_PLAYER_HAS_CARDS -> validatePlayerHasCardStepHandler.execute()
            MediatorEvent.VALIDATE_CARD_PLAYED -> validateCardPlayedStepHandler.execute()
            MediatorEvent.MAKE_PLAYER_GET_CARDS -> getCardStepHandler.execute()
            MediatorEvent.CHOSE_CARD -> choseCardStepHandler.execute()
            MediatorEvent.SELECT_COLOR_GAME -> selectColorStepHandler.execute()
            MediatorEvent.PLAYER_BLOCKED -> playerBlockedStepHandler.execute()
            MediatorEvent.REVERT_GAME_DIRECTION -> revertGameDirectionStepHandler.execute()
            MediatorEvent.ACTIVATE_SPECIAL_CARD_EFFECT -> specialCardEffectStepHandler.execute()
            MediatorEvent.NEXT_TURN -> nextTurnStepHandler.execute()
            else -> getCardStepHandler.execute()
        }
    }

}