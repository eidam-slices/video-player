package ui.utils

import org.jetbrains.compose.web.attributes.AttrsScope
import org.jetbrains.compose.web.css.CSSNumeric
import org.jetbrains.compose.web.css.StyleScope
import org.jetbrains.compose.web.css.padding
import org.w3c.dom.HTMLTrackElement
import org.w3c.dom.HTMLVideoElement

// region Video Attributes:
fun AttrsScope<HTMLVideoElement>.src(url: String): AttrsScope<HTMLVideoElement> = attr("src", url)
fun AttrsScope<HTMLVideoElement>.controls(): AttrsScope<HTMLVideoElement> = attr("controls", "")
// endregion

// region Track Attributes:
fun AttrsScope<HTMLTrackElement>.src(url: String): AttrsScope<HTMLTrackElement> = attr("src", url)
fun AttrsScope<HTMLTrackElement>.label(label: String): AttrsScope<HTMLTrackElement> = attr("label", label)
fun AttrsScope<HTMLTrackElement>.srclang(lang: String): AttrsScope<HTMLTrackElement> = attr("srclang", lang)
fun AttrsScope<HTMLTrackElement>.default(isDefault: Boolean = true): AttrsScope<HTMLTrackElement> = if (isDefault) {
    attr("default", "")
} else {
    this
}
// endregion

// region Common Attributes:
fun StyleScope.padding(top: CSSNumeric, right: CSSNumeric, bottom: CSSNumeric, left: CSSNumeric) =
    padding(top, right, bottom, left)

fun StyleScope.padding(vertical: CSSNumeric, horizontal: CSSNumeric) =
    padding(vertical, horizontal, vertical, horizontal)
// endregion