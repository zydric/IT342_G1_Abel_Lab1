package com.abel.mobile.data

data class AuthResponse(
    val token: String, // The JWT for protected routes
    val type: String = "Bearer",
    val username: String,
    val roles: List<String>
)
