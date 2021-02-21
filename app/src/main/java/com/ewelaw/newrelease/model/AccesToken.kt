package com.ewelaw.newrelease.model

import com.google.gson.annotations.SerializedName

data class AccesToken(
    @SerializedName("access_token")
    var accesToken: String = "",
    @SerializedName("token_type")
    var tokenType: String = ""
) {

    fun getAuthorizationString() : String{
        return "$tokenType $accesToken"
    }
}