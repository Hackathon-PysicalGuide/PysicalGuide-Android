package com.example.hackerton.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.hackerton.R
import com.example.hackerton.databinding.ActivitySignUpBinding
import com.example.hackerton.module.ContentType
import com.example.hackerton.module.RetrofitImpl
import com.example.hackerton.module.data.request.SignInDto
import com.example.hackerton.module.data.response.SignUpInResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpActivity : AppCompatActivity() {

    private lateinit var bind : ActivitySignUpBinding
    private val service = RetrofitImpl.service

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        bind =  ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(bind.root)

        val intent = Intent(this, LoginActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

        bind.signInBtn.setOnClickListener{
            val id = bind.signInId.text.toString()
            val pw = bind.signInPw.text.toString()
            val pwch = bind.signInPwCheck.text.toString()

            if(id.isEmpty() || pw.isEmpty() || pwch.isEmpty()){
                Toast.makeText(this, "모든항목을 채워주세요", Toast.LENGTH_SHORT).show()
            }else if (pw != pwch){
                Toast.makeText(this, "비밀번호가 다릅니다", Toast.LENGTH_SHORT).show()
            }else {
                val req =SignInDto(id,pw)
                service.signUp(ContentType.CONTENT_TYPE,req).enqueue( object : Callback<SignUpInResponse>{
                    override fun onResponse(call: Call<SignUpInResponse>, response: Response<SignUpInResponse>) {
                        Toast.makeText(this@SignUpActivity, "회원가입 성공", Toast.LENGTH_SHORT).show()
                        startActivity(intent)
                        finish()
                    }

                    override fun onFailure(call: Call<SignUpInResponse>, t: Throwable) {
                        Toast.makeText(this@SignUpActivity, "회원가입 실패", Toast.LENGTH_SHORT).show()
                        Log.e("회원가입 실패", t.message.toString())
                    }

                })
            }
        }
    }
}