package com.abel.mobile.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.abel.mobile.data.AuthResponse
import com.abel.mobile.data.LoginRequest
import com.abel.mobile.repository.UserRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel : ViewModel() {
    private val repository = UserRepository()

    // Internal mutable data
    private val _loginResult = MutableLiveData<AuthResponse?>()
    private val _error = MutableLiveData<String?>()
    private val _isLoading = MutableLiveData<Boolean>()

    // External immutable LiveData for the Activity to observe
    val loginResult: LiveData<AuthResponse?> = _loginResult
    val error: LiveData<String?> = _error
    val isLoading: LiveData<Boolean> = _isLoading

    fun login(username: String, password: String) {
        _isLoading.value = true
        val request = LoginRequest(username, password)

        repository.login(request).enqueue(object : Callback<AuthResponse> {
            override fun onResponse(call: Call<AuthResponse>, response: Response<AuthResponse>) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _loginResult.value = response.body()
                } else {
                    _error.value = "Login Failed: ${response.code()}"
                }
            }

            override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
                _isLoading.value = false
                _error.value = "Network Error: ${t.message}"
            }
        })
    }
}