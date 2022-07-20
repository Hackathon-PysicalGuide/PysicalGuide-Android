package com.example.hackerton.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.hackerton.R
import com.example.hackerton.module.ContentType
import com.example.hackerton.module.RetrofitImpl
import com.example.hackerton.module.data.request.SignInDto
import com.example.hackerton.module.data.response.SignUpInResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    private val service = RetrofitImpl.service

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val login = findViewById<View>(R.id.login_btn)

        val intent = Intent(this, MainActivity::class.java)

        login.setOnClickListener {
            val id = findViewById<TextView>(R.id.login_id).text.toString()
            val pw = findViewById<TextView>(R.id.login_pw).text.toString()

            if (id.isEmpty() || pw.isEmpty()) {
                Toast.makeText(this, "아이디와 비밀번호를 모두 입력해 주세요.",Toast.LENGTH_SHORT).show()
            }else {
                val req = SignInDto(id,pw)
                service.signIn(ContentType.CONTENT_TYPE, req).enqueue( object : Callback<SignUpInResponse>{
                    override fun onResponse(call: Call<SignUpInResponse>, response: Response<SignUpInResponse>) {
                        startActivity(intent)
                        finish()
                    }

                    override fun onFailure(call: Call<SignUpInResponse>, t: Throwable) {
                        Log.e("실패","로그인 실패 " + t.message)
                    }

                })
            }
        }
    }
}