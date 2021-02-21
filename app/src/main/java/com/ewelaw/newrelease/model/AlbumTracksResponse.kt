package com.ewelaw.newrelease.model

import com.google.gson.annotations.SerializedName

data class AlbumTracksResponse(
    val href: String,
    @SerializedName("items")
    val tracks: List<Track>,
    val limit: Int,
    val next: String,
    val offset: Int,
    val previous: String,
    val total: Int
)