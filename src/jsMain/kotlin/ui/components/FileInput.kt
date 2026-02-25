package ui.components

import androidx.compose.runtime.*
import org.jetbrains.compose.web.attributes.AttrsScope
import org.jetbrains.compose.web.attributes.InputType
import org.jetbrains.compose.web.css.DisplayStyle
import org.jetbrains.compose.web.css.display
import org.jetbrains.compose.web.dom.Button
import org.jetbrains.compose.web.dom.Input
import org.w3c.dom.HTMLButtonElement
import org.w3c.dom.HTMLInputElement
import org.w3c.files.File
import org.w3c.files.get

@Composable
fun FileInput(
    label: @Composable () -> Unit,
    onFileChange: (File?) -> Unit,
    attrs: AttrsScope<HTMLButtonElement>.() -> Unit = {}
) {

    var reference: HTMLInputElement? by remember { mutableStateOf(null) }


    Button(
        attrs = {
            attrs.invoke(this)
            onClick { reference?.click() }
        },
        content = { label() }
    )

    Input(
        type = InputType.File,
        attrs = {
            onInput { event ->
                val file = event.target.files?.get(0)
                onFileChange(file)
            }
            style {
                display(DisplayStyle.None)
            }
            ref { ref ->
                reference = ref
                onDispose { reference = null }
            }
        }
    )

}