package isel.pdm.demos.quoteofday.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import isel.pdm.demos.quoteofday.main.views.LoadingButton
import isel.pdm.demos.quoteofday.main.views.LoadingState
import isel.pdm.demos.quoteofday.main.views.QuoteView
import isel.pdm.demos.quoteofday.ui.theme.QuoteOfDayTheme

@Composable
fun QuoteOfDayScreen(
    quote: Quote? = null,
    loadingState: LoadingState = LoadingState.Idle,
    onUpdateRequested: () -> Unit = { }
) {
    QuoteOfDayTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            Column(horizontalAlignment = Alignment.End) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize().weight(weight = 1.0f),
                ) {
                    if (quote != null)
                        QuoteView(quote = quote)
                }
                Box(
                    contentAlignment = Alignment.CenterEnd,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    LoadingButton(
                        onClick = { onUpdateRequested() },
                        state = loadingState
                    )
                    // TODO: Add padding to the button
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun QuoteOfDayScreenPreview() {
    val quoteText = "O poeta é um fingidor.\n" +
            "Finge tão completamente\n" +
            "Que chega a fingir que é dor\n" +
            "A dor que deveras sente."

    val quote = Quote(quoteText, "Fernando Pessoa")
    QuoteOfDayTheme {
        QuoteOfDayScreen(quote)
    }
}

