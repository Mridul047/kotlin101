package com.mycodethesaurus.practice.http.model

import kotlinx.serialization.Serializable

@Serializable
data class ProductCategory(val slug: String, val name: String, val url: String) {
}