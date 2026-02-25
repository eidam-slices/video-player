package pages

import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.runtime.remember
import core.Router
import core.Routes
import data.ParameterSerializer
import domain.VideoParameters
import kotlinx.browser.window
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*
import org.w3c.dom.url.URLSearchParams
import ui.components.Icon
import ui.components.PageTitle
import ui.theme.Theme
import ui.utils.controls
import ui.utils.default
import ui.utils.label
import ui.utils.src


@Composable
fun PlayPage() {

    val parameters: VideoParameters? = remember(window.location.search) { resolveUrlParameters() }

    Div(
        attrs = {
            style {
                display(DisplayStyle.Flex)
                flexDirection(FlexDirection.Column)
                alignItems(AlignItems.Center)

                width(100.vw)
                height(100.vh)
                boxSizing("border-box")
                padding(8.px)
            }
        }
    ) {
        if (parameters != null) {
            parameters.run {
                PageTitle("Online Video Player")

                key(this.video) {
                    Video(
                        attrs = {
                            attr("crossorigin", "anonymous")
                            src(video)
                            controls()
                            style {
                                borderRadius(12.px)
                                minHeight(100.percent)
                                maxWidth(100.percent)
                                boxSizing("border-box")
                            }
                        }
                    ) {
                        for (subtitle in subtitles) {
                            key(subtitle.url) {
                                Track(
                                    attrs = {
                                        default(subtitle == subtitles.firstOrNull())
                                        src(subtitle.url)
                                        label(subtitle.label)
                                        attr("kind", "subtitles")

                                    }
                                )
                            }

                        }
                    }
                }
            }
        } else {
            PageTitle("Video Not Found")
            H2 { Text("Video not found") }

            Button(
                attrs = {
                    classes(Theme.styleSheet.add_subtitles_button)
                    onClick { Router.navigate(Routes.Index.path) }
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
                    Icon("arrow_back")
                    Text("Back to home page")
                }
            }
        }
    }


}

private fun resolveUrlParameters(): VideoParameters? {
    val parameters = URLSearchParams(window.location.search).get(VideoParameters.URL_PARAM_KEY) ?: run {
        console.error("No parameters found")
        return null
    }
    return ParameterSerializer.deserialize(parameters)
}