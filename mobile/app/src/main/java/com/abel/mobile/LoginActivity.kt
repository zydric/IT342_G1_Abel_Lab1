package com.abel.mobile.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.abel.mobile.MainActivity
import com.abel.mobile.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    // Initialize View Binding
    private lateinit var binding: ActivityLoginBinding

    // Initialize ViewModel using the 'by viewModels()' delegate
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupObservers()

        binding.btnLogin.setOnClickListener {
            val username = binding.etUsername.text.toString()
            val password = binding.etPassword.text.toString()

            if (username.isNotEmpty() && password.isNotEmpty()) {
                viewModel.login(username, password)
            } else {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setupObservers() {
        // Observe loading state to show/hide the orange progress bar
        viewModel.isLoading.observe(this) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
            binding.btnLogin.isEnabled = !isLoading
        }

        // Observe errors (e.g., 401 Unauthorized or Network Error)
        viewModel.error.observe(this) { errorMessage ->
            errorMessage?.let {
                Toast.makeText(this, it, Toast.LENGTH_LONG).show()
            }
        }

        // Observe success and navigate to the next screen
        viewModel.loginResult.observe(this) { response ->
            response?.let {
                Toast.makeText(this, "Welcome, ${it.username}!", Toast.LENGTH_SHORT).show()
                // Navigate to MainActivity (which will act as your Dashboard for now)
                startActivity(Intent(this, MainActivity::class.java))
                finish() // Close LoginActivity so user can't go back to it
            }
        }
    }
}