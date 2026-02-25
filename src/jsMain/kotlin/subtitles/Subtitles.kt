package subtitles

import kotlinx.browser.window
import kotlinx.coroutines.await
import org.w3c.dom.url.URL
import org.w3c.files.Blob
import org.w3c.files.BlobPropertyBag

class Subtitles(
    private val blob: Blob,
    private val originalUrl: String,
) {

    companion object {
        const val VTT_MIME_TYPE = "text/vtt"
        const val SRT_MIME_TYPE = "text/srt"
    }

    val url: String
        get() = URL.createObjectURL(blob)

    init {
        require(blob.type == "text/vtt") {
            "invalid subtitles - blob type does't match 'text/vtt'"
        }
    }
}

enum class SubtitlesFormat {
    VTT, SRT
}

object SubtitlesApi {

    private suspend fun fetchUrlContent(url: String): String {
        val response = window.fetch(url).await()

        if (!response.ok) {
            throw Exception("failed to load content of '$url': ${response.status}\n${response.statusText}")
        }
        return response.text().await()
    }


    suspend fun loadSubtitles(url: String): Subtitles {
        val text = fetchUrlContent(url)

        return when (recognizeFormat(text)) {
            SubtitlesFormat.VTT -> Subtitles(
                blob = Blob(
                    arrayOf(text),
                    BlobPropertyBag(Subtitles.VTT_MIME_TYPE)
                ),
                originalUrl = url
            )

            SubtitlesFormat.SRT -> Subtitles(
                blob = Blob(
                    arrayOf(convertSrtToVtt(text)),
                    BlobPropertyBag(Subtitles.VTT_MIME_TYPE)
                ),
                originalUrl = url
            )

            else -> throw Exception("unknown subtitles format of '$url'")
        }
    }


    private fun recognizeFormat(string: String): SubtitlesFormat? {
        val trimmed = string.trimStart()

        fun isVtt(): Boolean = trimmed.startsWith("WEBVTT", ignoreCase = true)
        fun isSrt(): Boolean {
            val timeFormat = """\d{2}:\d{2}:\d{2},\d{3}"""
            val lineFormat = """$timeFormat --> $timeFormat"""
            return Regex(lineFormat).containsMatchIn(trimmed)
        }

        return when {
            isVtt() -> SubtitlesFormat.VTT
            isSrt() -> SubtitlesFormat.SRT
            else -> null
        }
    }


    fun convertSrtToVtt(srtText: String): String {
        // 1. Definujeme vzor pro SRT časový kód (hledáme čárku mezi čísly)
        // Používáme zachytávací skupiny (), abychom zachovali čísla okolo
        val srtTimeRegex = Regex("""(\d{2}:\d{2}:\d{2}),(\d{3})""")

        // 2. Provedeme náhradu: $1 je čas před čárkou, $2 jsou milisekundy
        val repairedTimes = srtText.replace(srtTimeRegex, "$1.$2")

        // 3. Sestavíme finální VTT soubor s povinnou hlavičkou
        return "WEBVTT\n\n$repairedTimes"
    }
}

suspend fun fetchUrlContent(url: String): String {
    val response = window.fetch(url).await()

    if (!response.ok) {
        throw Exception("failed to load content from $url: ${response.status}\n${response.statusText}")
    }

    return response.text().await()
}




val example_srt = """
    1
    00:00:00,000 --> 00:00:02,500
    Welcome to the Example Subtitle File!

    2
    00:00:03,000 --> 00:00:06,000
    This is a demonstration of SRT subtitles.

    3
    00:00:07,000 --> 00:00:10,500
    You can use SRT files to add subtitles to your videos.

    4
    00:00:12,000 --> 00:00:15,000
    Each subtitle entry consists of a number, a timecode,
    and the subtitle text.

    5
    00:00:16,000 --> 00:00:20,000
    The timecode format is hours:minutes:seconds,milliseconds.
""".trimIndent()

val example_vtt = """
WEBVTT

00:00:00.500 --> 00:00:02.000
The Web is always changing

00:00:02.500 --> 00:00:04.300
and the way we access it is changing
""".trimIndent()