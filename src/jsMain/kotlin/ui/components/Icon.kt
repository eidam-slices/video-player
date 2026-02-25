package ui.components

import androidx.compose.runtime.Composable
import org.jetbrains.compose.web.css.CSSNumeric
import org.jetbrains.compose.web.css.fontSize
import org.jetbrains.compose.web.css.lineHeight
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Span
import org.jetbrains.compose.web.dom.Text

@Composable
fun Icon(name: String, size: CSSNumeric = 24.px) {
    Div(
        attrs = {
            style {
                lineHeight(0.px)
            }
        }
    ) {

        Span(
            attrs = {
                classes("material-symbols-outlined")
                style {
                    fontSize(size)
                    property("user-select", "none")
                }
            }
        ) {
            Text(name)
        }
    }

}