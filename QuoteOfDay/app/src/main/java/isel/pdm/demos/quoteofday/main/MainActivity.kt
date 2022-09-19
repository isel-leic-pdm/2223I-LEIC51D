package isel.pdm.demos.quoteofday.main

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import isel.pdm.demos.quoteofday.main.views.LoadingState

const val TAG = "QuoteOfDayApp"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val loadingState = remember {
                Log.v(TAG, "Inside remember function")
                mutableStateOf(LoadingState.Idle)
            }
            QuoteOfDayScreen(
                quote = null,
                onUpdateRequested = {
                    loadingState.value = LoadingState.Loading
                },
                loadingState = loadingState.value
            )
        }
    }
}
