package com.mycodethesaurus.practice.http

import com.mycodethesaurus.practice.http.model.ProductCategory
import com.mycodethesaurus.practice.http.model.Products
import kotlinx.serialization.json.Json
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import kotlin.system.measureTimeMillis

fun main() {

    println("Enter following input:\n" + "1-> Get all products categories\n" + "2-> Get all groceries products\n")

    // READ USER INPUT
    val userInput = readln()

    // INITIALIZE VARIABLES
    var getProductCategoriesResponse: List<ProductCategory>? = null
    var getProductsResponse: Products? = null


    if (userInput == "1") {
        // CALL GET API
        val response1Time = measureTimeMillis {
            getProductCategoriesResponse = getProductCategories(getApiUrl("1"))
        }

        // PRINT GET API RESPONSE
        println("GET API getProductCategories executed in Time: $response1Time ms with response: $getProductCategoriesResponse")
    }

    if (userInput == "2") {
        val response2Time = measureTimeMillis {
            getProductsResponse = getProducts(getApiUrl("2"))
        }

        // PRINT GET API RESPONSE
        println("GET API getProducts executed in Time: $response2Time ms with response: $getProductsResponse")
    }

}

fun getProducts(serviceUrl: String): Products {
    val http = HttpClient.newHttpClient()
    val response = http.send(buildHttpRequest(serviceUrl), HttpResponse.BodyHandlers.ofString())
    return Json.Default.decodeFromString<Products>(response.body())
}

fun getProductCategories(serviceUrl: String): List<ProductCategory> {
    val http = HttpClient.newHttpClient()
    val response = http.send(buildHttpRequest(serviceUrl), HttpResponse.BodyHandlers.ofString())
    return Json.Default.decodeFromString<List<ProductCategory>>(response.body())
}

private fun buildHttpRequest(serviceUrl: String): HttpRequest {
    return HttpRequest.newBuilder()
        .uri(URI.create(serviceUrl))
        .GET()
        .build()
}

private fun getApiUrl(userInput: String): String {
    return when (userInput) {
        "1" -> "https://dummyjson.com/products/categories"
        "2" -> "https://dummyjson.com/products/category/groceries"
        else -> throw IllegalArgumentException("Invalid user input: $userInput")
    }
}

private fun sendMessage(s: String = "Default String") {

    when {
        s.toIntOrNull() != null -> println("User input is number: $s")
        s.all { it.isLetter() } -> println("User input is all letter: $s")
        else -> println("User input is alphanumeric: $s")

    }
}