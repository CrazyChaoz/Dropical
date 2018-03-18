package at.dropical.server.logicState

import at.dropical.shared.fromHell.GameState

// Created by julian on 01.03.18.
/**
 * A class with this interface capable of
 * changing the GameLogicState.
 */
interface StateManager {

    fun changeGameState(state: GameState)
    fun resetGame()
}