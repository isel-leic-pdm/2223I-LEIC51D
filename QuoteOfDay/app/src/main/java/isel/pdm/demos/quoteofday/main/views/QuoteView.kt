package isel.pdm.demos.quoteofday.main.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import isel.pdm.demos.quoteofday.main.Quote
import isel.pdm.demos.quoteofday.ui.theme.QuoteOfDayTheme

@Composable
fun QuoteView(quote: Quote) {
    Column(modifier = Modifier
        .padding(64.dp)
        .testTag("QuoteView")) {
        Text(
            text = quote.content,
            style = MaterialTheme.typography.h6,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            textAlign = TextAlign.Start
        )
        Text(
            text = quote.author,
            style = MaterialTheme.typography.subtitle1,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.End
        )
    }
}

@Preview
@Composable
fun PreviewQuoteView() {
    QuoteOfDayTheme {
        val quoteText = "O poeta é um fingidor.\n" +
                "Finge tão completamente\n" +
                "Que chega a fingir que é dor\n" +
                "A dor que deveras sente."

        val quote = Quote(quoteText, "Fernando Pessoa")
        QuoteView(quote = quote)
    }
}
