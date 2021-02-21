package com.ewelaw.newrelease.api

import android.net.Credentials
import android.telecom.Call
import com.ewelaw.newrelease.model.AccesToken
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface AuthorizationApi {
    @POST("api/token")
    fun getAccesToken(
        @Header("Content-Type") contentType : String,
                              @Header("Authorization") credentials: String,
                              @Query("grant_type") grant_type: String
    ): retrofit2.Call<AccesToken>
}