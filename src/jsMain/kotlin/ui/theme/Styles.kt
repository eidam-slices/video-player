package ui.theme

import org.jetbrains.compose.web.css.*


class AppStyleClass(flavor: CatppuccinFlavor) : StyleSheet() {

    val theme = flavor

//    var isDark = false
//    val theme = if (isDark) Catppuccin.Mocha
//    else Catppuccin.Latte

    init {
        // remove default margin & padding
        "html, body" style {
            margin(0.px) // nemazat

            backgroundColor(theme.base())
            color(theme.text())
            minWidth(100.vw)
            minHeight(100.vh)
            boxSizing("border-box")
        }

        // set font for body and inherit it for other elements
        "body" style {
            fontFamily("Rubik")
        }
        "input, button, textarea, select" style {
            fontFamily("inherit")
        }
    }

    private fun CSSBuilder.buttonStyle() {
        cursor("pointer")
        fontWeight(600)

        borderRadius(roundedCornerRadius)

        backgroundColor(theme.blue())
        color(theme.base())
        border(0.px)
        padding(4.px, 16.px)
        outline("2.px solid ${theme.blue()}")

        self + hover style {
            backgroundColor(theme.blue.autoHover()())
        }
        self + active style {
            property("scale", "0.98")
            property("transform", "translateY(1px)")
        }
    }

    val roundedCornerRadius = 20.px


    val button by style {
        buttonStyle()
    }

    val add_subtitles_button by style {
        buttonStyle()
        fontWeight(500)

        self + disabled style {
            backgroundColor(theme.surface2())
            color(theme.base())
            property("pointer-events", "none")

        }
    }

    val subtitles_input_container by style {
        display(DisplayStyle.Flex)
        gap(0.px)
        flex(1)
        minWidth(0.px)
    }

    val subtitles_textfield by style {
        width(25.vw)
        backgroundColor(theme.surface0())
        color(theme.text())
        border(1.px, LineStyle.Solid, theme.surface1())

        borderRadius(
            topLeft = roundedCornerRadius,
            bottomLeft = roundedCornerRadius,
            topRight = 0.px,
            bottomRight = 0.px
        )

        padding(8.px, 12.px)
        outline("none")
        border(0.px)

        self + focus style {

        }

        self + hover style {
            backgroundColor(theme.surface0.autoHover()())
        }
    }

    val subtitles_browse_button by style {

        buttonStyle()

        borderRadius(
            topRight = roundedCornerRadius,
            bottomRight = roundedCornerRadius,
            topLeft = 0.px,
            bottomLeft = 0.px
        )

        backgroundColor(theme.surface0())
        color(theme.text())

        padding(8.px, 16.px)
        cursor("pointer")
        fontWeight(500)
        fontSize(14.px)

        self + hover style {
            backgroundColor(theme.surface0.autoHover()())
        }
        self + active style {
            property("scale", "1")
            property("transform", "translateY(0px)")
            backgroundColor(theme.surface0.autoHover(0.1)())
        }
    }

    val text_field by style {
        backgroundColor(theme.surface0())
        color(theme.text())
        border(0.px)

        borderRadius(24.px)
        fontSize(20.px)
        padding(12.px, 20.px)
        outline("none")

        self + focus style {
        }

        self + hover style {
            backgroundColor(theme.surface0.autoHover()())
        }

    }


}


//object AppStyle : StyleSheet() {
//
//
//    val theme = Catppuccin.Mocha
//
////    var isDark = false
////    val theme = if (isDark) Catppuccin.Mocha
////    else Catppuccin.Latte
//
//    init {
//        // remove default margin & padding
//        "html, body" style {
//            margin(0.px) // nemazat
//
//            backgroundColor(theme.base())
//            color(theme.text())
//            minWidth(100.vw)
//            minHeight(100.vh)
//            boxSizing("border-box")
//        }
//
//        // set font for body and inherit it for other elements
//        "body" style {
//            fontFamily("Rubik")
//        }
//        "input, button, textarea, select" style {
//            fontFamily("inherit")
//        }
//    }
//
//    private fun CSSBuilder.buttonStyle() {
//        cursor("pointer")
//        fontWeight(600)
//
//        borderRadius(roundedCornerRadius)
//
//        backgroundColor(theme.blue())
//        color(theme.base())
//        border(0.px)
//        padding(4.px, 16.px)
//        outline("2.px solid ${theme.blue()}")
//
//        self + hover style {
//            backgroundColor(theme.blue.autoHover()())
//        }
//        self + active style {
//            property("scale", "0.98")
//            property("transform", "translateY(1px)")
//        }
//    }
//
//    val roundedCornerRadius = 20.px
//
//
//    val button by style {
//        buttonStyle()
//    }
//
//    val add_subtitles_button by style {
//        buttonStyle()
//        fontWeight(500)
//
//        self + disabled style {
//            backgroundColor(theme.surface2())
//            color(theme.base())
//            property("pointer-events", "none")
//
//        }
//    }
//
//    val subtitles_input_container by style {
//        display(DisplayStyle.Flex)
//        gap(0.px)
//        flex(1)
//        minWidth(0.px)
//    }
//
//    val subtitles_textfield by style {
//        width(25.vw)
//        backgroundColor(theme.surface0())
//        color(theme.text())
//        border(1.px, LineStyle.Solid, theme.surface1())
//
//        borderRadius(
//            topLeft = roundedCornerRadius,
//            bottomLeft = roundedCornerRadius,
//            topRight = 0.px,
//            bottomRight = 0.px
//        )
//
//        padding(8.px, 12.px)
//        outline("none")
//        border(0.px)
//
//        self + focus style {
//
//        }
//
//        self + hover style {
//            backgroundColor(theme.surface0.autoHover()())
//        }
//    }
//
//    val subtitles_browse_button by style {
//
//        buttonStyle()
//
//        borderRadius(
//            topRight = roundedCornerRadius,
//            bottomRight = roundedCornerRadius,
//            topLeft = 0.px,
//            bottomLeft = 0.px
//        )
//
//        backgroundColor(theme.surface0())
//        color(theme.text())
//
//        padding(8.px, 16.px)
//        cursor("pointer")
//        fontWeight(500)
//        fontSize(14.px)
//
//        self + hover style {
//            backgroundColor(theme.surface0.autoHover()())
//        }
//        self + active style {
//            property("scale", "1")
//            property("transform", "translateY(0px)")
//            backgroundColor(theme.surface0.autoHover(0.1)())
//        }
//    }
//
//    val text_field by style {
//        backgroundColor(theme.surface0())
//        color(theme.text())
//        border(0.px)
//
//        borderRadius(24.px)
//        fontSize(20.px)
//        padding(12.px, 20.px)
//        outline("none")
//
//        self + focus style {
//        }
//
//        self + hover style {
//            backgroundColor(theme.surface0.autoHover()())
//        }
//
//    }
//
//}