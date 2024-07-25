package com.alawneh.shoppingapplicationtesting.data.remote

data class ImageResponse(
    val hits: List<Hit>,
    val total: Int,
    val totalHits: Int
)