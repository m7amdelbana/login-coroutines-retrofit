package com.m7amdelbana.coroutineswithrotrofit.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.m7amdelbana.coroutineswithrotrofit.*
import com.m7amdelbana.coroutineswithrotrofit.network.API
import com.m7amdelbana.coroutineswithrotrofit.network.AuthService
import com.m7amdelbana.coroutineswithrotrofit.network.SignForm
import com.m7amdelbana.coroutineswithrotrofit.network.Token
import kotlinx.coroutines.runBlocking
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    lateinit var edtEmail: EditText
    lateinit var edtPassword: EditText
    lateinit var btnSignIn: Button

    val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(API.BASE_URL)
        .build()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()

        btnSignIn.setOnClickListener {
            val authService = retrofit.create(
                AuthService::class.java
            )

            val email = edtEmail.text.toString().trim()
            val password = edtPassword.text.toString().trim()

            runBlocking {

                // ----------- OBJECT -----------
//                val token: Token = authService.login(SignForm(email, password))
//                Log.v("API_TOKEN", token.accessToken)

                // ----------- RESPONSE -----------
                val response = authService.login(
                    SignForm(
                        email,
                        password
                    )
                )
                if (response.code() == 200) {
                    val token: Token? = response.body()
                    Log.v("API_TOKEN", token!!.accessToken)
                } else {
                    Log.v("API_TOKEN", response.message())
                }

                // ----------- CALL // Error -----------
//                val token: Token = try {
//                    authService.login(SignForm(email, password)).await()
//                } catch (e: Exception) {
//                    println("Network error")
//                    Log.v("API_TOKEN", e.message)
//                    return@runBlocking
//                }
//                Log.v("API_TOKEN", token.accessToken)
            }
        }
    }

    private fun init() {
        edtEmail = findViewById(R.id.email_editText)
        edtPassword = findViewById(R.id.password_editText)
        btnSignIn = findViewById(R.id.sign_in_button)

    }
}
