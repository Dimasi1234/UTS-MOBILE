package com.example.blog

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.blog.api.ApiClient
import com.example.blog.api.LoginRequest
import com.example.blog.api.LoginResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    private lateinit var etUsername: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnLogin: Button
    private lateinit var cbRemember: CheckBox
    private lateinit var tvSignup: TextView
    private lateinit var tvForgot: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        etUsername = findViewById(R.id.etUsername)
        etPassword = findViewById(R.id.etPassword)
        btnLogin = findViewById(R.id.btnLogin)
        cbRemember = findViewById(R.id.cbRemember)
        tvSignup = findViewById(R.id.tvSignup)
        tvForgot = findViewById(R.id.tvForgot)

        btnLogin.setOnClickListener {
            login()
        }

        tvSignup.setOnClickListener {
            Toast.makeText(this, "Signup belum dibuat", Toast.LENGTH_SHORT).show()
        }

        tvForgot.setOnClickListener {
            Toast.makeText(this, "Forgot Password belum tersedia", Toast.LENGTH_SHORT).show()
        }
    }

    private fun login() {
        val username = etUsername.text.toString().trim()
        val password = etPassword.text.toString().trim()

        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Semua field harus diisi", Toast.LENGTH_SHORT).show()
            return
        }

        val request = LoginRequest(username, password)

        ApiClient.apiService.loginUser(request).enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful && response.body()?.status == true) {
                    Toast.makeText(this@LoginActivity, "Login sukses", Toast.LENGTH_SHORT).show()
                    // Ganti ke MainActivity
                    startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                    finish()
                } else {
                    Toast.makeText(this@LoginActivity, "Login gagal: ${response.body()?.message}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Toast.makeText(this@LoginActivity, "Gagal konek ke server: ${t.message}", Toast.LENGTH_LONG).show()
            }
        })
    }
}
