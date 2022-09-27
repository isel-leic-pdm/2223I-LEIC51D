package isel.pdm.demos.quoteofday

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.test.runner.AndroidJUnitRunner
import isel.pdm.demos.quoteofday.daily.Quote
import isel.pdm.demos.quoteofday.daily.QuoteOfDayService
import kotlinx.coroutines.delay

const val FAKE_FETCH_DELAY = 2000L

private class TestFakeQuoteService : QuoteOfDayService {
    override suspend fun getTodayQuote(): Quote {
        Log.v(TAG, "TestFakeQuoteService.getTodayQuote()")
        delay(FAKE_FETCH_DELAY)
        return Quote("Test text", "Test author")
    }
}

class QuoteOfDayTestApplication : DependenciesContainer, Application() {
    override val quoteService: QuoteOfDayService by lazy { TestFakeQuoteService() }
}

@Suppress("unused")
class QuoteOfDayTestRunner : AndroidJUnitRunner() {
    override fun newApplication(cl: ClassLoader, className: String, context: Context): Application {
        Log.v(TAG, "QuoteOfDayTestRunner.newApplication()")
        return super.newApplication(cl, QuoteOfDayTestApplication::class.java.name, context)
    }
}