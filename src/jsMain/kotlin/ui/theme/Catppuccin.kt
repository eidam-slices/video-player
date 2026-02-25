package ui.theme

interface CatppuccinFlavor {
    val rosewater: Color
    val flamingo: Color
    val pink: Color
    val mauve: Color
    val red: Color
    val maroon: Color
    val peach: Color
    val yellow: Color
    val green: Color
    val teal: Color
    val sky: Color
    val sapphire: Color
    val blue: Color
    val lavender: Color
    val text: Color
    val subtext1: Color
    val subtext0: Color
    val overlay2: Color
    val overlay1: Color
    val overlay0: Color
    val surface2: Color
    val surface1: Color
    val surface0: Color
    val base: Color
    val mantle: Color
    val crust: Color
}

object Catppuccin {
    object Mocha : CatppuccinFlavor {
        override val rosewater = Color.hex("#f2d5cf")
        override val flamingo = Color.hex("#eebebe")
        override val pink = Color.hex("#f4b8e4")
        override val mauve = Color.hex("#ca9ee6")
        override val red = Color.hex("#e78284")
        override val maroon = Color.hex("#ea999c")
        override val peach = Color.hex("#ef9f76")
        override val yellow = Color.hex("#e5c890")
        override val green = Color.hex("#a6d189")
        override val teal = Color.hex("#81c8be")
        override val sky = Color.hex("#99d1db")
        override val sapphire = Color.hex("#85c1dc")
        override val blue = Color.hex("#8caaee")
        override val lavender = Color.hex("#babbf1")
        override val text = Color.hex("#c6d0f5")
        override val subtext1 = Color.hex("#b5bfe2")
        override val subtext0 = Color.hex("#a5adce")
        override val overlay2 = Color.hex("#949cbb")
        override val overlay1 = Color.hex("#838ba7")
        override val overlay0 = Color.hex("#737994")
        override val surface2 = Color.hex("#626880")
        override val surface1 = Color.hex("#51576d")
        override val surface0 = Color.hex("#414559")
        override val base = Color.hex("#303446")
        override val mantle = Color.hex("#292c3c")
        override val crust = Color.hex("#232634")
    }
    object Latte : CatppuccinFlavor {
        override val rosewater = Color.hex("#dc8a78")
        override val flamingo = Color.hex("#dd7878")
        override val pink = Color.hex("#ea76cb")
        override val mauve = Color.hex("#8839ef")
        override val red = Color.hex("#d20f39")
        override val maroon = Color.hex("#e64553")
        override val peach = Color.hex("#fe640b")
        override val yellow = Color.hex("#df8e1d")
        override val green = Color.hex("#40a02b")
        override val teal = Color.hex("#179299")
        override val sky = Color.hex("#04a5e5")
        override val sapphire = Color.hex("#209fb5")
        override val blue = Color.hex("#1e66f5")
        override val lavender = Color.hex("#7287fd")
        override val text = Color.hex("#4c4f69")
        override val subtext1 = Color.hex("#5c5f77")
        override val subtext0 = Color.hex("#6c6f85")
        override val overlay2 = Color.hex("#7c7f93")
        override val overlay1 = Color.hex("#8c8fa1")
        override val overlay0 = Color.hex("#9ca0b0")
        override val surface2 = Color.hex("#acb0be")
        override val surface1 = Color.hex("#bcc0cc")
        override val surface0 = Color.hex("#ccd0da")
        override val base = Color.hex("#eff1f5")
        override val mantle = Color.hex("#e6e9ef")
        override val crust = Color.hex("#dce0e8")
    }
}