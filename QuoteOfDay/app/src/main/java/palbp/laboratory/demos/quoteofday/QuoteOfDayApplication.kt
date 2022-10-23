package palbp.laboratory.demos.quoteofday

import android.app.Application
import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.coroutines.delay
import okhttp3.Cache
import okhttp3.OkHttpClient
import palbp.laboratory.demos.quoteofday.quotes.*
import palbp.laboratory.demos.quoteofday.utils.hypermedia.SubEntity
import palbp.laboratory.demos.quoteofday.utils.hypermedia.SubEntityDeserializer
import java.net.URL

const val TAG = "QuoteOfDayDemo"

interface DependenciesContainer {
    val quoteService: QuoteService
}

private val quoteAPIHome = URL("https://1bbd-2001-818-e22f-ee00-e13e-7fa1-2552-28a.ngrok.io")

class QuoteOfDayApplication : DependenciesContainer, Application() {

    private val httpClient: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .cache(Cache(directory = cacheDir, maxSize = 50 * 1024 * 1024))
            .build()
    }

    private val jsonEncoder: Gson by lazy {
        GsonBuilder()
            .registerTypeHierarchyAdapter(
                SubEntity::class.java,
                SubEntityDeserializer<QuoteDtoProperties>(QuoteDtoProperties::class.java)
            )
            .create()
    }

    override val quoteService: QuoteService by lazy {
        RealQuoteService(
            httpClient = httpClient,
            jsonEncoder = jsonEncoder,
            quoteHome = quoteAPIHome
        )
    }

    override fun onCreate() {
        super.onCreate()
        Log.v(TAG, "QuoteOfDayApplication.onCreate() on process ${android.os.Process.myPid()}")
    }
}


@Suppress("unused")
private class FakeQuoteService : QuoteService {
    private val aQuote = Quote(
        text = "O poeta é um fingidor.\n" +
                "Finge tão completamente\n" +
                "Que chega a fingir que é dor\n" +
                "A dor que deveras sente.",
        author = "Fernando Pessoa"
    )

    override suspend fun fetchQuote(): Quote {
        delay(3000)
        return aQuote
    }

    override suspend fun fetchWeekQuotes(): List<Quote> {
        delay(3000)
        return buildList { repeat(20) { add(aQuote) } }
    }
}

