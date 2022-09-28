package isel.pdm.demos.quoteofday

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner
import isel.pdm.demos.quoteofday.daily.Quote
import isel.pdm.demos.quoteofday.daily.QuoteOfDayService


private class TestFakeQuoteService : QuoteOfDayService {
    override suspend fun getTodayQuote() =
        Quote("Test text", "Test author")
}

class QuoteOfDayTestApplication : DependenciesContainer, Application() {
    override val quoteService: QuoteOfDayService by lazy { TestFakeQuoteService() }
}

@Suppress("unused")
class QuoteOfDayTestRunner : AndroidJUnitRunner() {
    override fun newApplication(cl: ClassLoader, className: String, context: Context): Application {
        return super.newApplication(cl, QuoteOfDayTestApplication::class.java.name, context)
    }
}