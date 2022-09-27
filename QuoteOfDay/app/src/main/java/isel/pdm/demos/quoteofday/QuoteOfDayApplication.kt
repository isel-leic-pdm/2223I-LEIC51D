package isel.pdm.demos.quoteofday

import android.app.Application
import isel.pdm.demos.quoteofday.daily.FakeQuoteOfDayService
import isel.pdm.demos.quoteofday.daily.QuoteOfDayService

const val TAG = "QuoteOfDayApp"

class QuoteOfDayApplication : DependenciesContainer, Application() {
    override val quoteService: QuoteOfDayService
        // TODO: Exchange for the real implementation
        get() = FakeQuoteOfDayService()
}

interface DependenciesContainer {
    val quoteService: QuoteOfDayService
}
