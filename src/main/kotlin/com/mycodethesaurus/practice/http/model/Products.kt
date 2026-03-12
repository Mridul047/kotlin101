package com.mycodethesaurus.practice.http.model

import kotlinx.serialization.Serializable

@Serializable
data class Products(val products: List<Product>, val total: Int, val skip: Int, val limit: Int)
