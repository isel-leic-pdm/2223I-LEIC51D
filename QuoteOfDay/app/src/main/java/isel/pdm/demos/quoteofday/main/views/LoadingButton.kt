package isel.pdm.demos.quoteofday.main.views

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import isel.pdm.demos.quoteofday.R
import isel.pdm.demos.quoteofday.ui.theme.QuoteOfDayTheme


enum class LoadingState { Idle, Loading }

@Composable
fun LoadingButton(state: LoadingState, onClick: () -> Unit = { }) {
    Button(onClick = onClick, enabled = state == LoadingState.Idle) {
        val buttonTextId =
            if (state == LoadingState.Idle) R.string.fetch_quote_button_text
            else R.string.fetch_quote_button_text_loading
        Text(text = stringResource(id = buttonTextId))
    }
}

@Preview
@Composable
fun LoadingButtonPreviewIdle() {
    QuoteOfDayTheme {
        LoadingButton(state = LoadingState.Idle)
    }
}

@Preview
@Composable
fun LoadingButtonPreviewLoading() {
    QuoteOfDayTheme {
        LoadingButton(state = LoadingState.Loading)
    }
}