//package at.htlwels.it.wollersbergerjulian.tetris.server.main
//
//import at.dropical.server.logicState.StateManager
//import at.dropical.shared.GameState
//
//
//// Created by julian on 01.02.18.
///**
// * This class handles the different players/games running at the same time.
// * (Theoretically even more than two)
// *
// * This gets called from the LocalClient.
// */
//class MultiPlayerManager : StateManager {
//    /* A list of all (usually 2) games that run. */
//    private val playersMap = HashMap<Int, SinglePlayer>()
//
//
////    fun handleClientRequest(request: PollRequest): PollRequest {
////        val players = playersMap[request.playerNo]
////        if(players == null) {
////            // Make the new game
////            val newGameLoop = SinglePlayer(this)
////            playersMap.put(request.playerNo, newGameLoop)
////
////            return newGameLoop.run(request)
////        } else
////            return players.run(request)
////    }
//
//    /** If one player clears some lines,
//     * they are added to the other/all other players.
//     * TODO be able to activate / deactivate that from the client for singleplayer.
//     *
//     * @param playerNo The player that clear lines.
//     * No lines are added to him.
//     * @param lines the number of lines. At max 4. */
//    internal fun addLinesToAll(callingPlayer: SinglePlayer, lines: Int) {
//        if(lines<0 || lines>=4)
//            throw IllegalArgumentException("Line number must be between [0;4], but is $lines")
//
//        // For every player
//        playersMap.forEach { nb, player ->
//            if(player != callingPlayer)
//                player.addLines(lines)
//        }
//    }
//
//    /** This function is called by the current
//     * gameLogicState in SinglePlayer.
//     *
//     * fixme Doesn't work with win/lose */
//    override fun changeGameState(state: GameState) {
//        // For every player
//        playersMap.forEach { nb, player ->
//            player.changeGameState(state)
//        }
//    }
//
//    /** All the initialising happens immediately and automatically
//     * when the client sends the next PollRequest. */
//    override fun resetGame() {
//        playersMap.clear()
//    }
//}