package com.bouyahya.chatmultiplatform.core.network

import com.bouyahya.composemultiplatformtutorial.core.error.ServerException
import io.ktor.client.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

internal fun createHttpClient(enableLogging: Boolean): HttpClient {
    return createPlatformHttpClient().config {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
            })
        }

        HttpResponseValidator {
            validateResponse { response ->
                val statusCode = response.status.value
                println("HTTP status: $statusCode")
                println("response: $response")
                when (statusCode) {
                    !in 200..299 -> {
                        throw ServerException(
                            errorMessage = Json.parseToJsonElement(response.bodyAsText()).jsonObject["message"]?.jsonPrimitive?.content
                                ?: "Server Error"
                        )
                    }
                }
            }

            handleResponseExceptionWithRequest { cause: Throwable, _ ->
                throw cause
            }
        }

        install(DefaultRequest) {
            header(
                "Authorization",
                "Bearer token"
            )
        }

        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.ALL
        }
    }
}