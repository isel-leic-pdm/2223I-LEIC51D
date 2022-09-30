package isel.pdm.demos.quoteofday.daily

import android.util.Log
import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import isel.pdm.demos.quoteofday.TAG
import isel.pdm.demos.quoteofday.utils.loggableMutableStateOf
import kotlinx.coroutines.launch

class DailyQuoteViewModel(
    private val quoteService: QuoteOfDayService
) : ViewModel() {

    private val _isLoading = loggableMutableStateOf(
        at = "DailyQuoteViewModel.isLoading",
        value = false
    )
    val isLoading: State<Boolean>
        get() = _isLoading

    private val _quote = loggableMutableStateOf<Quote?>(
        at = "DailyQuoteViewModel.quote",
        value = null
    )
    val quote: State<Quote?>
        get() = _quote

    fun fetchQuote() {
        viewModelScope.launch {
            Log.v(TAG, "fetchQuote() coroutine starts")
            _isLoading.value = true
            _quote.value = quoteService.getTodayQuote()
            _isLoading.value = false
            Log.v(TAG, "fetchQuote() coroutine ends")
        }
    }
}