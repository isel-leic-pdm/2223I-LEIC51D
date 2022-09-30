package isel.pdm.demos.quoteofday.daily

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import isel.pdm.demos.quoteofday.DependenciesContainer
import isel.pdm.demos.quoteofday.daily.views.LoadingState
import isel.pdm.demos.quoteofday.info.InfoActivity

class DailyQuoteActivity : ComponentActivity() {

    private val quoteService: QuoteOfDayService by lazy {
        (application as DependenciesContainer).quoteService
    }

    @Suppress("UNCHECKED_CAST")
    private val vm by viewModels<DailyQuoteViewModel> {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return DailyQuoteViewModel(quoteService) as T
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val loadingState =
                if (vm.isLoading.value) LoadingState.Loading
                else LoadingState.Idle
            QuoteOfDayScreen(
                quote = vm.quote.value,
                onUpdateRequest = { vm.fetchQuote() },
                onInfoRequest = { navigateToInfoScreen() },
                loadingState = loadingState
            )
        }
    }

    private fun navigateToInfoScreen() {
        val intent = Intent(this, InfoActivity::class.java)
        startActivity(intent)
    }
}
