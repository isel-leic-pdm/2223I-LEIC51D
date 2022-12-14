package isel.pdm.demos.quoteofday.daily.views

import android.util.Log
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import isel.pdm.demos.quoteofday.R
import isel.pdm.demos.quoteofday.TAG
import isel.pdm.demos.quoteofday.ui.theme.QuoteOfDayTheme


enum class LoadingState { Idle, Loading }

@Composable
fun LoadingButton(
    state: LoadingState,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = { }
) {
    Log.v(TAG, "LoadingButton composing ")
    Button(
        onClick = onClick,
        enabled = state == LoadingState.Idle,
        modifier = modifier.testTag("LoadingButton")
    ) {
        val buttonTextId =
            if (state == LoadingState.Idle) R.string.activity_quote_fetch_button_text_idle
            else R.string.activity_quote_fetch_button_text_loading
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
