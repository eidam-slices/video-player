package pages

import androidx.compose.runtime.*
import core.Router
import core.Routes
import data.ParameterSerializer
import domain.Track
import domain.VideoParameters
import org.jetbrains.compose.web.attributes.disabled
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.Button
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.H1
import org.jetbrains.compose.web.dom.Text
import ui.components.Icon
import ui.components.PageTitle
import ui.components.SubtitlesInput
import ui.components.VideoInput
import ui.theme.Theme

@Composable
fun IndexPage() {

    val state = rememberSelectionState()

    PageTitle("Online Video Player")

    Div(
        attrs = {
            style {
                display(DisplayStyle.Flex)
                flexDirection(FlexDirection.Column)
                alignItems(AlignItems.Center)
                gap(16.px)
                minWidth(100.vw)
                minHeight(100.vh)
                padding(8.px)
                boxSizing("border-box")
            }
        }
    ) {

        // Heading
        H1(
            attrs = {
                style {
                    padding(0.px)
                }
            }
        ) { Text("Video Player") }

        // Input Fields
        Div(
            attrs = {
                style {
                    display(DisplayStyle.Flex)
                    flexDirection(FlexDirection.Column)
                    maxWidth(1000.px)
                    gap(8.px)
                }
            }
        )
        {

            VideoInput(
                placeholder = "Enter video URL or select a file",
                value = state.video,
                onValueChange = { url ->
                    state.video = url
                    console.log("Selected source: $url")
                }
            )
            RenderSubtitles(state)

            Button(
                attrs = {
                    classes(Theme.styleSheet.add_subtitles_button)
                    onClick {
                        if (state.subtitles.lastOrNull()?.url?.isNotEmpty() ?: true) {
                            state.subtitles += Track(
                                url = "",
                                label = "",
                            )
                        }
                    }
                    style {
                        minWidth(100.percent)
                    }
                }
            ) { Text("Add subtitles") }
        }

        // Play Button
        Button(
            attrs = {
                if (state.video.isEmpty()) {
                    disabled()
                }
                classes(Theme.styleSheet.add_subtitles_button)
                onClick {
                    val url = Routes.VideoPlayer.withParam(
                        VideoParameters.URL_PARAM_KEY
                                to ParameterSerializer.serialize(state.toParameters())
                    )
                    Router.navigate(url)
                }
            }
        ) {
            Div(
                attrs = {
                    style {
                        display(DisplayStyle.Flex)
                        flexDirection(FlexDirection.Row)
                        alignItems(AlignItems.Center)
                        gap(4.px)
                    }
                }
            ) {
                Icon("play_arrow")
                Text("Play")
            }
        }

        // Change Theme Button
        Button(
            attrs = {
                classes(Theme.styleSheet.add_subtitles_button)
                onClick { Theme.toggle() }
                style {
                    position(Position.Absolute)
                    top(16.px)
                    right(16.px)
                }

            }
        ) {
            Icon(
                name = if (Theme.isDark) {
                    "light_mode"
                } else {
                    "dark_mode"
                }
            )
        }

    }


}

@Composable
private fun RenderSubtitles(state: SelectionState) {
    state.subtitles.forEachIndexed { index, subtitle ->
        SubtitlesInput(
            placeholder = "Enter subtitles URL or select a file",
            value = subtitle,
            onValueChanged = {
                state.subtitles = state.subtitles.toMutableList().apply {
                    this[index] = it
                }
            },
        )
    }
}

@Composable
private fun rememberSelectionState(): SelectionState {
    return remember { SelectionState() }
}


private class SelectionState(
    initialVideo: String = "",
    initialSubtitles: List<Track> = emptyList(),
) {

    var video by mutableStateOf(initialVideo)

    var subtitles: List<Track> by mutableStateOf(initialSubtitles)


    private companion object {

        const val EXAMPLE_VIDEO = ""

        val EXAMPLE_SUBTITLES = listOf(
            Track(
                label = "",
                url = "",
            ),
            /*Track(
                label = "",
                url = "",
            ),*/
        )
    }
}

private fun SelectionState.toParameters(): VideoParameters {
    return VideoParameters(
        video = video,
        subtitles = subtitles
    )
}