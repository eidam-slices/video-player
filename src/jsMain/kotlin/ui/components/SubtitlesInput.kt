package ui.components

import androidx.compose.runtime.Composable
import domain.Track
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.Div
import org.w3c.dom.url.URL
import ui.theme.Theme


@Composable
fun SubtitlesInput(
    value: Track,
    onValueChanged: (Track) -> Unit,
    placeholder: String,
) {
    Div(
        attrs = {
            classes(Theme.styleSheet.subtitles_input_container)
            style {
                width(100.percent)
            }
        }
    ) {
        TextInput(
            value = value.label,
            onValueChange = {
                onValueChanged(value.copy(label = it))
            },
            placeholder = "Label",
            attrs = {
                classes(Theme.styleSheet.subtitles_textfield)
                style {
                    textAlign("center")
                    width(48.px)
                    paddingRight(4.px)
                }
            }
        )

        TextInput(
            value = value.url,
            onValueChange = {
                onValueChanged(value.copy(url = it))
            },
            placeholder = placeholder,
            attrs = {
                classes(Theme.styleSheet.subtitles_textfield)

                style {
                    paddingLeft(4.px)
                    paddingRight(4.px)
                    borderRadius(0.px)
                    width(100.percent)
                }
            },
        )

        FileInput(
            label = {
                Icon("folder", size = 20.px)
            },
            onFileChange = { file ->
                file?.let {
                    val localUrl = URL.createObjectURL(file)
                    onValueChanged(value.copy(url = localUrl))
                }
            },
            attrs = {
                classes(Theme.styleSheet.subtitles_browse_button)
                style {
                    paddingLeft(4.px)
                    paddingRight(8.px)
                }
            }
        )
    }
}
