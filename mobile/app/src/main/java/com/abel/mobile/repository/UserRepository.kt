package com.abel.mobile.repository

import retrofit2.Call
import com.abel.mobile.data.AuthResponse
import com.abel.mobile.data.LoginRequest
import com.abel.mobile.data.RetrofitClient
import kotlin.math.log

class UserRepository {
    private val authService = RetrofitClient.instance

    fun login(loginRequest: LoginRequest): Call<AuthResponse> {
        return authService.login(loginRequest)
    }
}