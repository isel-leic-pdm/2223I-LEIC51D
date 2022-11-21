package palbp.laboratory.demos.tictactoe.lobby

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import palbp.laboratory.demos.tictactoe.DependenciesContainer
import palbp.laboratory.demos.tictactoe.TAG
import palbp.laboratory.demos.tictactoe.game.GameActivity
import palbp.laboratory.demos.tictactoe.preferences.PreferencesActivity
import palbp.laboratory.demos.tictactoe.utils.viewModelInit

/**
 * The screen used to display the list of players in the lobby, that is, available to play.
 */
class LobbyActivity : ComponentActivity() {

    private val viewModel by viewModels<LobbyScreenViewModel> {
        viewModelInit {
            val lobby = (application as DependenciesContainer).lobby
            LobbyScreenViewModel(lobby)
        }
    }

    companion object {
        fun navigate(context: Context) {
            with(context) {
                val intent = Intent(this, LobbyActivity::class.java)
                startActivity(intent)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val players = viewModel.players.collectAsState()
            LobbyScreen(
                state = LobbyScreenState(players.value),
                onBackRequested = { finish() },
                onPreferencesRequested = {
                    PreferencesActivity.navigate(this, finishOnSave = true)
                },
                onPlayerSelected = { GameActivity.navigate(this) }
            )
        }

        lifecycle.addObserver(object : DefaultLifecycleObserver {
            override fun onStart(owner: LifecycleOwner) {
                viewModel.enterLobby()
            }

            override fun onStop(owner: LifecycleOwner) {
                viewModel.leaveLobby()
            }
        })
    }
}