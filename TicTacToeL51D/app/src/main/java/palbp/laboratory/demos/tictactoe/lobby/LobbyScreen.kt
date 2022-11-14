package palbp.laboratory.demos.tictactoe.lobby

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import palbp.laboratory.demos.tictactoe.ui.NavigationHandlers
import palbp.laboratory.demos.tictactoe.ui.TopBar
import palbp.laboratory.demos.tictactoe.ui.theme.TicTacToeTheme

const val LobbyScreenTag = "LobbyScreen"

@Composable
fun LobbyScreen(
    onBackRequested: () -> Unit,
    onPreferencesRequested: () -> Unit
) {
    TicTacToeTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize().testTag(LobbyScreenTag),
            topBar = {
                TopBar(
                    NavigationHandlers(onBackRequested, onPreferencesRequested)
                )
            },
        ) { innerPadding ->
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(innerPadding).fillMaxSize(),
            ) {
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    LobbyScreen(
        onBackRequested = { },
        onPreferencesRequested = { }
    )
}