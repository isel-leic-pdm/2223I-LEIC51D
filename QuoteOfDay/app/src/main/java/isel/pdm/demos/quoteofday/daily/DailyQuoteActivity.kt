package isel.pdm.demos.quoteofday.daily

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import isel.pdm.demos.quoteofday.DependenciesContainer
import isel.pdm.demos.quoteofday.daily.views.LoadingState

class DailyQuoteActivity : ComponentActivity() {

    private val vm by viewModels<DailyQuoteViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val service = (application as DependenciesContainer).quoteService
        setContent {
            val loadingState =
                if (vm.isLoading.value) LoadingState.Loading
                else LoadingState.Idle
            QuoteOfDayScreen(
                quote = vm.quote.value,
                onUpdateRequested = { vm.fetchQuote(service) },
                loadingState = loadingState
            )
        }
    }
}
