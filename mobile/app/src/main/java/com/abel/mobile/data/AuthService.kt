package com.abel.mobile.data

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("api/auth/signin")
    fun login(@Body request: LoginRequest): Call<AuthResponse>
}