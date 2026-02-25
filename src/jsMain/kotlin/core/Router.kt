package core

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import kotlinx.browser.window


object Router {
    private var _path: MutableState<String> = mutableStateOf(window.location.pathname)
    val path by _path

    fun navigate(path: String) {
        if (path == _path.value) return

        window.history.pushState(
            null, "", path,
        )
        this._path.value = path
    }

    fun back() {
        window.history.back()
    }

    fun sync() {
        _path.value = window.location.pathname + window.location.search
    }
}