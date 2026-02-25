package core

abstract class Route(val path: String) {

    fun withParams(params: Map<String, String>): String {
        return buildString {
            append(path)
            var first = true
            for ((key, value) in params) {
                check(key.isNotBlank()) { "Parameter key cannotbe blank" }
                check(value.isNotBlank()) { "Parameter value cannot be blank" }

                if (first) append("?").also { first = false }
                else append("&")
                append(key)
                append("=").append(value)
            }
        }
    }

    fun withParam(parameter: Pair<String, String>): String = withParams(mapOf(parameter))
}

object Routes {
    private val all: MutableList<Route> = mutableListOf()
    private fun route(path: String): Route = object : Route(path) {}.also { all.add(it) }

    fun match(path: String): Route? {
        val cleanPath = path.substringBefore("?")
        return all.singleOrNull { it.path == cleanPath }
    }

    val Index = route("/")
    val VideoPlayer = route("/play")
}