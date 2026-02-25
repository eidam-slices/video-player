package data

@JsModule("lz-string")
@JsNonModule
external object LZStringJS {
    fun compressToEncodedURIComponent(input: String): String
    fun decompressFromEncodedURIComponent(input: String): String?
}