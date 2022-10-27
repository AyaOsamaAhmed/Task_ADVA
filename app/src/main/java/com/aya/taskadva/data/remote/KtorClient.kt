package com.aya.taskadva.data.remote

import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.*
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.serialization.json.Json


object KtorClient {

    private val BASE_URL: String = ""
    private const val AUTHORIZATION_HEADER = "Authorization"

    private val client = HttpClient(Android) {
        defaultRequest {
            host = BASE_URL
            url {
                protocol = URLProtocol.HTTPS
            }
        //   header(AUTHORIZATION_HEADER, "BEARER $API_KEY")
        }
        install(JsonFeature) {
            serializer = KotlinxSerializer(
                Json {
                    isLenient = true
                    ignoreUnknownKeys = true
                    explicitNulls = false
                })
        }
    }

    val getInstance = client
}