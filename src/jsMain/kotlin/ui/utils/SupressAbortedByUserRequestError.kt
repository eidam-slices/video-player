package ui.utils

import kotlinx.browser.window

fun supressStupidAbortedByUserRequestError() {
    window.addEventListener(
        type = "unhandledrejection",
        callback = {
            val error = it.asDynamic()
            if (error.reason.name == "AbortError" && error != null) {
                console.warn("Supressed stupid abortion error: ${error.reason.message}")
                it.preventDefault()
                it.stopImmediatePropagation()
            }
        },
        options = true,
    )
}
