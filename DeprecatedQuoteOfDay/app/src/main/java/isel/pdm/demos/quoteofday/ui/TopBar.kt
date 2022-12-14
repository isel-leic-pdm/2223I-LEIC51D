package isel.pdm.demos.quoteofday.ui

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import isel.pdm.demos.quoteofday.R
import isel.pdm.demos.quoteofday.ui.theme.QuoteOfDayTheme

@Composable
fun TopBar(
    onBackRequested: (() -> Unit)? = null,
    onInfoRequested: (() -> Unit)? = null,
) {
    TopAppBar(
        title = { Text(text = stringResource(id = R.string.app_name)) },
        navigationIcon = {
            if (onBackRequested != null) {
                IconButton(onClick = onBackRequested) {
                    Icon(Icons.Default.ArrowBack, contentDescription = null)
                }
            }
        },
        actions = {
            if (onInfoRequested != null) {
                IconButton(onClick = onInfoRequested) {
                    Icon(Icons.Default.Info, contentDescription = "Localized description")
                }
            }
        }
    )
}

@Preview
@Composable
fun TopBarPreviewDailyQuoteScreen() {
    QuoteOfDayTheme {
        TopBar(onInfoRequested = { })
    }
}

@Preview
@Composable
fun TopBarPreviewAboutScreen() {
    QuoteOfDayTheme {
        TopBar(onBackRequested = { })
    }
}