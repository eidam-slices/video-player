package pages

import androidx.compose.runtime.Composable
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Span
import org.jetbrains.compose.web.dom.Text
import ui.components.Icon
import ui.components.PageTitle

@Composable
fun NotFound() {

    PageTitle("404 - Not Found")

    Div(
        attrs = {
            style {
                width(100.vw)
                height(100.vh)
                boxSizing("border-box")
                display(DisplayStyle.Flex)
                justifyContent(JustifyContent.Center)
                alignItems(AlignItems.Center)
            }
        }
    ) {

        Div(
            attrs = {
                style {
                    display(DisplayStyle.Flex)
                    flexDirection(FlexDirection.Row)
                    alignItems(AlignItems.Center)
                    gap(8.px)
                }
            }
        ) {

            Icon("error",
                size = 36.px
            )

            Span(
                attrs = {
                    style {
                        fontSize(24.px)
                    }
                }
            ) {
                Text("404 - Not Found")
            }
        }
    }
}
