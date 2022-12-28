package palbp.laboratory.demos.tictactoe.game.lobby

import androidx.test.platform.app.InstrumentationRegistry
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.tasks.await
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement
import palbp.laboratory.demos.tictactoe.TicTacToeTestApplication
import palbp.laboratory.demos.tictactoe.game.lobby.adapters.LOBBY
import palbp.laboratory.demos.tictactoe.game.lobby.adapters.LobbyFirebase
import palbp.laboratory.demos.tictactoe.game.lobby.adapters.toDocumentContent
import palbp.laboratory.demos.tictactoe.game.lobby.domain.Lobby
import palbp.laboratory.demos.tictactoe.otherTestPlayersInLobby

class PopulatedFirebaseLobby : TestRule {

    val app: TicTacToeTestApplication by lazy {
        InstrumentationRegistry
            .getInstrumentation()
            .targetContext
            .applicationContext as TicTacToeTestApplication
    }

    val lobby: Lobby = LobbyFirebase(app.emulatedFirestoreDb)

    private suspend fun populateLobby() {
        otherTestPlayersInLobby.forEach {
            app.emulatedFirestoreDb
                .collection(LOBBY)
                .document(it.id.toString())
                .set(it.info.toDocumentContent())
                .await()
        }
    }

    private suspend fun emptyLobby() {
        app.emulatedFirestoreDb
            .collection(LOBBY)
            .get()
            .await()
            .forEach {
                app.emulatedFirestoreDb
                    .collection(LOBBY)
                    .document(it.id)
                    .delete()
                    .await()
            }
    }


    override fun apply(test: Statement, description: Description): Statement =
        object : Statement() {
            override fun evaluate() = runBlocking {
                try {
                    populateLobby()
                    test.evaluate()
                }
                finally {
                    emptyLobby()
                }
            }
        }
}
