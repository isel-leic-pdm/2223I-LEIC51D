package palbp.laboratory.demos.tictactoe.main

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTests {

    @get:Rule
    val testRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun screen_contains_start_button() {
        testRule.onNodeWithTag("MainScreen").assertExists()
        testRule.onNodeWithTag("PlayButton").assertExists()
    }

    @Test
    fun pressing_play_navigates_to_lobby_if_user_info_exists() {

        // Arrange not required. Default testing repo always returns a UserInfo instance

        // Act
        testRule.onNodeWithTag("PlayButton").performClick()
        testRule.waitForIdle()

        // Assert
        testRule.onNodeWithTag("LobbyScreen").assertExists()
    }
}