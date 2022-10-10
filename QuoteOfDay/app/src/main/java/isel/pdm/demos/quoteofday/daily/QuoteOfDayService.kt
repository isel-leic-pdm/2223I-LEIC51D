package isel.pdm.demos.quoteofday.daily

import android.util.Log
import com.google.gson.Gson
import isel.pdm.demos.quoteofday.TAG
import isel.pdm.demos.quoteofday.utils.hypermedia.SirenEntity
import isel.pdm.demos.quoteofday.utils.hypermedia.SirenMediaType
import kotlinx.coroutines.delay
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import java.io.IOException
import java.net.URL
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

interface QuoteOfDayService {
    suspend fun getTodayQuote(): Quote
}

class RealQuoteOfDayService(
    private val httpClient: OkHttpClient,
    private val gson: Gson,
    private val homeUrl: URL
) : QuoteOfDayService {

    override suspend fun getTodayQuote(): Quote {
        val request = Request.Builder().url(homeUrl).build()
        val quote = suspendCoroutine { continuation ->
            httpClient.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    Log.v(TAG, "got failure response on Thread = ${Thread.currentThread().name}")
                    continuation.resumeWithException(e)
                }

                override fun onResponse(call: Call, response: Response) {
                    Log.v(TAG, "got response on Thread = ${Thread.currentThread().name}")
                    val contentType = response.body?.contentType()
                    if (response.isSuccessful && contentType != null && contentType == SirenMediaType) {
                        val dto = gson.fromJson<QuoteDto>(
                            response.body?.string(), QuoteDtoType.type
                        )
                        continuation.resume(dto.toQuote())
                    }
                    else {
                        TODO()
                    }
                }
            })
        }
        return quote
    }
}

class FakeQuoteOfDayService : QuoteOfDayService {
    override suspend fun getTodayQuote(): Quote {
        val quoteText = "O poeta é um fingidor.\n" +
                "Finge tão completamente\n" +
                "Que chega a fingir que é dor\n" +
                "A dor que deveras sente."

        val quote = Quote(quoteText, "Fernando Pessoa")
        delay(3000)
        return quote
    }
}