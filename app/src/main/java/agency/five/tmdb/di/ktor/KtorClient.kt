package agency.five.tmdb.di.ktor

import android.util.Log
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.kotlinx.serializer.*
import io.ktor.client.plugins.logging.*
import kotlinx.serialization.json.Json

fun KtorClient() {

    val httpClient: HttpClient = HttpClient(Android) {
        /*
        install(JsonFeature) {
            serializer = KotlinxSerializer(kotlinx.serialization.json.Json)
        }*/
        install(Logging) {
            logger = object : Logger {
                override fun log(message: String) {
                    Log.d("HTTP", message)
                }
            }
            level = LogLevel.ALL
        }
    }

}