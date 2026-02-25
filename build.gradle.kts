plugins {
    alias(libs.plugins.kotlin)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.compose)

    alias(libs.plugins.serialization)
}

group = "cz.eidam"
version = "1.0-SNAPSHOT"

kotlin {
    js(IR) {
        browser {
            commonWebpackConfig {
                outputFileName = "main.js"
                devServer = devServer?.copy(
                    open = false,
                    client = devServer?.client?.copy(
                        overlay = false
                    ),
                )
            }
            testTask {
                useKarma {
                    useFirefoxHeadless()
                }
            }
        }
        binaries.executable()
    }

    sourceSets {
        jsMain.dependencies {
            implementation(libs.compose.html.core)
            implementation(libs.compose.runtime)
            // implementation(libs.compose.viewmodel)

            // serialization json:
            implementation(libs.serialization.json)
            // url compression:
            implementation(npm("lz-string", libs.versions.lzstring.get()))

        }
        jsTest.dependencies {
            implementation(kotlin("test-js"))
        }
    }
}
