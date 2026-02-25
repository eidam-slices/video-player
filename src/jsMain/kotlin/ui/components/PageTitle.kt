package ui.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import kotlinx.browser.document

@Composable
fun PageTitle(title: String) {
    LaunchedEffect(Unit) {
        document.title = title
    }
}