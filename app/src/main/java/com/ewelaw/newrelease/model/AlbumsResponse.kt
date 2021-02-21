package com.ewelaw.newrelease.model

data class AlbumsResponse(
    val albums: Albums,
    val total: Int,
    val maxPages : Int
)