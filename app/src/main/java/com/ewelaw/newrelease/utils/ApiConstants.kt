package com.ewelaw.newrelease.utils

import android.util.Base64
import kotlinx.serialization.toUtf8Bytes

object ApiConstants {
    const val AUTH_URL = "https://accounts.spotify.com/"
    const val BASE_URL = "https://api.spotify.com/v1/"
    private const val CLIENT_ID = "83f7f0ec6334430aaa2f0beb7eefeb35"
    private const val CLIENT_SECRET = "c3371cae0a2442118fb7a0dad3dc51d9"
    const val GRANT_TYPE = "client_credentials"
    const val CONTENT_TYPE = "application/x-www-form-urlencoded"
    private const val STR_AUTH = "$CLIENT_ID:$CLIENT_SECRET"
    val ENCODED_AUTHORIZATION = " Basic " + Base64.encodeToString(STR_AUTH.toUtf8Bytes(), Base64.NO_WRAP)
    var TOKEN = ""
    const val ABUMS_PAGE_SIZE = 10
}