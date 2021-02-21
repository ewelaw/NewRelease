package com.ewelaw.newrelease.api

import com.ewelaw.newrelease.model.AlbumTracksResponse
import com.ewelaw.newrelease.model.AlbumsResponse
import com.ewelaw.newrelease.utils.ApiConstants
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface AlbumApi {
    @GET("browse/new-releases")
    suspend fun getAllNewReleases(
        @Query("country") country: String,
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): AlbumsResponse

    @GET("albums/{id}/tracks")
    suspend fun getAlbumTracks(
        @Path("id") id : String) : AlbumTracksResponse
}