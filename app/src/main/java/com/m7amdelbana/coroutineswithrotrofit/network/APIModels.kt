package com.m7amdelbana.coroutineswithrotrofit.network

import com.google.gson.annotations.SerializedName

data class SignForm(
    @SerializedName("email") var email: String,
    @SerializedName("password") var password: String
)

data class Token(
    @SerializedName("x-auth-token") var accessToken: String
)