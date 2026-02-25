package data

import domain.VideoParameters
import kotlinx.serialization.json.Json

object ParameterSerializer {
    private val json by lazy {
        Json {
            // skip defaults (e.g. no subtitles are not shown in url)
            encodeDefaults = false
        }
    }


    fun serialize(parameters: VideoParameters): String {
        val jsonified = json.encodeToString(parameters)
        val compressed = LZStringJS.compressToEncodedURIComponent(jsonified)
        return compressed
    }

    fun deserialize(serialized: String): VideoParameters? {
        val decompressed = LZStringJS.decompressFromEncodedURIComponent(serialized).takeIf { it.asDynamic() != null }
            ?: return null
        return json.decodeFromString(decompressed)
    }


}