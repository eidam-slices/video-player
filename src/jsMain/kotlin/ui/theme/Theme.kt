package ui.theme

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.browser.localStorage

object Theme {

    var isDark: Boolean by mutableStateOf(getFromLocalStorage())
        private set

    val styleSheet by derivedStateOf {
        if (!isDark) LightStyleSheet
        else DarkStyleSheet
    }

    fun toggle() {
        isDark = !isDark
        setToLocalStorage(isDark)
    }

    private const val LOCAL_STORAGE_KEY = "is_dark"

    private fun getFromLocalStorage(): Boolean {
        @Suppress("SimpleRedundantLet")
        return localStorage.getItem(LOCAL_STORAGE_KEY)?.let { stored ->
            stored.toBooleanStrictOrNull()
        } ?: false
    }

    private fun setToLocalStorage(value: Boolean) {
        localStorage.setItem(LOCAL_STORAGE_KEY, value.toString())
    }

    private val LightStyleSheet by lazy {
        AppStyleClass(Catppuccin.Latte)
    }
    private val DarkStyleSheet by lazy {
        AppStyleClass(Catppuccin.Mocha)
    }
}

