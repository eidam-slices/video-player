import core.Router
import core.Routes
import kotlinx.browser.window
import org.jetbrains.compose.web.css.Style
import org.jetbrains.compose.web.renderComposable
import pages.IndexPage
import pages.NotFound
import pages.PlayPage
import ui.theme.Theme
import ui.utils.supressStupidAbortedByUserRequestError


fun main() {
    supressStupidAbortedByUserRequestError()

    // ? TODO: should it be extracted?
    window.addEventListener("popstate", {
        Router.sync()
    })



    // ? TODO: should it be extracted?
    window.onbeforeunload = { event ->
        val message = "You will lose your progress and video will not be reloaded if it's local. Are you sure?"
        event.asDynamic().returnValue = message
        message
    }

    renderComposable(rootElementId = "root") {
        Style(Theme.styleSheet)

        // Navigation:
        when (val path = Routes.match(Router.path)) {
            Routes.Index -> IndexPage()
            Routes.VideoPlayer -> PlayPage()
            else -> NotFound()
        }
    }
}



