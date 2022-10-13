package isel.pdm.demos.quoteofday

import android.app.Application
import com.google.gson.Gson
import isel.pdm.demos.quoteofday.daily.FakeQuoteOfDayService
import isel.pdm.demos.quoteofday.daily.QuoteOfDayService
import isel.pdm.demos.quoteofday.daily.RealQuoteOfDayService
import okhttp3.OkHttpClient
import java.net.URL

const val TAG = "QuoteOfDayApp"

private val API_HOME = "https://b8b4-193-236-165-2.ngrok.io/"

class QuoteOfDayApplication : DependenciesContainer, Application() {
    override val jsonFormatter: Gson
        get() = Gson()
    override val httpClient: OkHttpClient
        get() = OkHttpClient()
    override val quoteService: QuoteOfDayService
        get() = RealQuoteOfDayService(httpClient, jsonFormatter, URL(API_HOME))
}

interface DependenciesContainer {
    val quoteService: QuoteOfDayService
    val jsonFormatter: Gson
    val httpClient: OkHttpClient
}
