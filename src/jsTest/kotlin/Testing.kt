import core.Routes
import data.LZStringJS
import kotlinx.serialization.json.Json
import kotlin.test.Test

class Test {
    @Test
    fun test() {

        val route = Routes.VideoPlayer

        val params = mapOf(
            "a" to "1",
            "b" to "2",
            "c" to "3"
        )

        println("route with params:")
        println(route.withParams(params))

        val serialized = Json.encodeToString(params)
        println("serialized: $serialized")
        val compressed = LZStringJS.compressToEncodedURIComponent(serialized)
        println("compressed: $compressed")

        val decompressed = LZStringJS.decompressFromEncodedURIComponent(compressed)
        println("decompressed: $decompressed")

        fun randomChar(): String {
            return (('a'..'z') + ('A'..'Z') + ('0'..'9')).random().toString()
        }

        if (decompressed == null) {
            println("decompressed is null")
            return
        }

        val fuckedDecompressed = List(4) { randomChar() }.joinToString("") +
                decompressed.drop(4).dropLast(3) +
                List(3) { randomChar() }.joinToString("")

        println("fucked decompressed: $fuckedDecompressed")

        if (serialized == decompressed) {
            println("success: decompressed matches serialized")
        } else {
            println("something fucked up: decompressed does not match serialized")
        }

        if (fuckedDecompressed != serialized) {
            println("successs: fucked decompressed does not match serialized")
        } else {
            println("something fucked up: fucked decompressed matches serialized")
        }


    }
}