package ui.components

import androidx.compose.runtime.Composable
import org.jetbrains.compose.web.attributes.InputType
import org.jetbrains.compose.web.attributes.builders.InputAttrsScope
import org.jetbrains.compose.web.attributes.placeholder
import org.jetbrains.compose.web.dom.Input

@Composable
fun TextInput(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String? = null,
    attrs: InputAttrsScope<String>.() -> Unit = {}
) {
    Input(
        type = InputType.Text,
        attrs = {
            attrs.invoke(this)
            placeholder?.let {
                placeholder(it)
            }
            value(value)
            onInput { event ->
                onValueChange(event.value)
            }
        }
    )
}
