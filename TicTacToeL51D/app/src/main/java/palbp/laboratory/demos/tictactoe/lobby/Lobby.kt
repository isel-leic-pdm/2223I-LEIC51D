package palbp.laboratory.demos.tictactoe.lobby

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import palbp.laboratory.demos.tictactoe.preferences.UserInfo


/**
 * Abstraction that characterizes the game's lobby, using a push-style
 * interaction model, a.k.a reactive style.
 */
interface Lobby {
    val players: Flow<List<UserInfo>>
    suspend fun getPlayers(): List<UserInfo>
}

class FakeLobby : Lobby {

    private var count = 1

    private fun getCurrentListOfPlayers(): List<UserInfo> {
        val list = buildList {
            repeat(5) {
                add(UserInfo("My Nick $it", "$count This is my $it moto"))
            }
        }
        count += 1
        return list
    }


    override suspend fun getPlayers() = getCurrentListOfPlayers()

    override val players: Flow<List<UserInfo>>
        get() = flow {
            while (true) {
                delay(5000)
                emit(getCurrentListOfPlayers())
            }
        }
}
