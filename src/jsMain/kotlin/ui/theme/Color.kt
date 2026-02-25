package ui.theme

import org.jetbrains.compose.web.css.CSSColorValue
import org.jetbrains.compose.web.css.StylePropertyValue
import kotlin.math.roundToInt


class Color private constructor(val r: Int, val g: Int, val b: Int, val a: Double = 1.0): StylePropertyValue {

    companion object {
        private val HEX_FORMAT by lazy {
            HexFormat {
                upperCase = true
                number {
                    minLength = 2
                    removeLeadingZeros = false
                }
            }
        }

        fun hex(hex: String): Color {
            val rgba = hex.removePrefix("#").chunked(2).map {
                it.hexToUByte(HEX_FORMAT).toInt()
            }
            val alpha = rgba.getOrNull(3)?.div(255.0) ?: 1.0
            return Color(rgba[0], rgba[1], rgba[2], a = alpha)
        }

        fun rgba(r: Int, g: Int, b: Int, a: Double): Color {
            require(listOf(r, g, b).all { it in 0..255 }) {
                "RGB values must be in the range 0-255"
            }
            require(a in 0.0..1.0) {
                "Alpha value must be in the range 0.0-1.0"
            }
            return Color(r, g, b, a)
        }

        fun rgb(r: Int, g: Int, b: Int): Color {
            return rgba(r, g, b, a = 1.0)
        }
    }


    operator fun invoke(): CSSColorValue = org.jetbrains.compose.web.css.rgba(r, g, b, a)


    val rgb: Triple<Int, Int, Int>
        get() = Triple(r, g, b)

    val hex: String
        get() {
            val alpha = (a * 255).roundToInt()

            return "#" + listOf(r, g, b, alpha).joinToString("") { int ->
                int.toUByte().toHexString(HEX_FORMAT)
            }
        }


    fun copy(r: Int = this.r, g: Int = this.g, b: Int = this.b, a: Double = this.a): Color {
        return Color(r, g, b, a)
    }

    operator fun component1(): Int = r
    operator fun component2(): Int = g
    operator fun component3(): Int = b
    operator fun component4(): Double = a

    override fun equals(other: Any?): Boolean {
        return other is Color && r == other.r && g == other.g && b == other.b && a == other.a
    }

    override fun hashCode(): Int {
        var result = r
        result = 31 * result + g
        result = 31 * result + b
        result = 31 * result + a.hashCode()
        return result
    }
}

fun Color.autoHover(factor: Double = 0.05): Color {
    val brightness = (r * 299 + g * 587 + b * 114) / 1000
    return if (brightness > 128) {
        this.copy(
            r = (r * (1 - factor)).toInt(),
            g = (g * (1 - factor)).toInt(),
            b = (b * (1 - factor)).toInt()
        )
    } else {
        this.copy(
            r = (r + (255 - r) * factor).toInt(),
            g = (g + (255 - g) * factor).toInt(),
            b = (b + (255 - b) * factor).toInt()
        )
    }
}
