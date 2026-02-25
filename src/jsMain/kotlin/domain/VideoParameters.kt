package domain

import kotlinx.serialization.Serializable

@Serializable
data class VideoParameters(
    val video: String,
    val subtitles: List<Track> = emptyList()
) {
    companion object {
        const val URL_PARAM_KEY = "data"
    }
}

@Serializable
data class Track(
    val label: String,
    val url: String,
)