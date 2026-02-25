package ui.components

import androidx.compose.runtime.Composable
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.Div
import org.w3c.dom.url.URL
import ui.theme.Theme

@Composable
fun VideoInput(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
) {
    Div(
        attrs = {
            style {
                display(DisplayStyle.Flex)
                minHeight(56.px)
                gap(4.px)
            }
        }
    ) {

        TextInput(
            value = value,
            onValueChange = { onValueChange(it) },
            placeholder = placeholder,
            attrs = {
                classes(Theme.styleSheet.text_field)
                style {
                    width(35.vw)
                    borderRadius(
                        topRight = 0.px,
                        bottomRight = 0.px,
                        bottomLeft = Theme.styleSheet.roundedCornerRadius,
                        topLeft = Theme.styleSheet.roundedCornerRadius
                    )
                }
            }
        )

        FileInput(
            label = {
                Icon("folder")
            },
            onFileChange = { file ->
                file?.let {
                    val localUrl = URL.createObjectURL(file)
                    onValueChange(localUrl)
                }
            },
            attrs = {
                classes(Theme.styleSheet.button)
                style {
                    borderRadius(
                        topRight = Theme.styleSheet.roundedCornerRadius,
                        bottomRight = Theme.styleSheet.roundedCornerRadius,
                        bottomLeft = 0.px,
                        topLeft = 0.px
                    )
                }
            }
        )
    }
}
