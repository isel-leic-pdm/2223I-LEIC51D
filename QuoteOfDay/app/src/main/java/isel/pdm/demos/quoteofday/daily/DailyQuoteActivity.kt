package isel.pdm.demos.quoteofday.daily

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import isel.pdm.demos.quoteofday.daily.views.LoadingState
import isel.pdm.demos.quoteofday.utils.loggableMutableStateOf
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

const val TAG = "QuoteOfDayApp"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val service = FakeQuoteOfDayService()
        setContent {
            val loadingState = remember {
                Log.v(TAG, "Inside remember loadingState")
                loggableMutableStateOf(
                    at = "root.loadingState",
                    value = LoadingState.Idle
                )
            }
            val scope = rememberCoroutineScope()
            val quote = remember {
                Log.v(TAG, "Inside remember quote")
                loggableMutableStateOf<Quote?>(
                    at = "root.quote",
                    value = null
                )
            }
            QuoteOfDayScreen(
                quote = quote.value,
                onUpdateRequested = {
                    scope.launch {
                        Log.v(TAG, "onUpdateRequested()")
                        loadingState.value = LoadingState.Loading
                        quote.value = service.getTodayQuote()
                        loadingState.value = LoadingState.Idle
                        Log.v(TAG, "onUpdateRequested() ends")
                    }
                },
                loadingState = loadingState.value
            )
        }
    }
}
