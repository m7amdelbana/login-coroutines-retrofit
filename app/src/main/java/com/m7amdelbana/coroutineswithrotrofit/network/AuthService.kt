package com.m7amdelbana.coroutineswithrotrofit.network

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {

    @POST(API.USERS_LOGIN)
    suspend fun login(@Body signForm: SignForm): Response<Token>
}