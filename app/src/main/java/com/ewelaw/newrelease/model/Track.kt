package com.ewelaw.newrelease.model

data class Track(
    val artists: List<Artist>,
    val disc_number: Int,
    val duration_ms: Int,
    val explicit: Boolean,
    val external_urls: ExternalUrls,
    val href: String,
    val id: String,
    val is_local: Boolean,
    val is_playable: Boolean,
    val name: String,
    val preview_url: String,
    val track_number: Int,
    val type: String,
    val uri: String
)