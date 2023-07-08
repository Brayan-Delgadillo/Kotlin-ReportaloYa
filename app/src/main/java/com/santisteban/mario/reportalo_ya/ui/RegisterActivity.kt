package com.santisteban.mario.reportalo_ya.ui

import android.content.Intent
import android.os.Bundle
import com.santisteban.mario.reportalo_ya.User
import com.santisteban.mario.reportalo_ya.databinding.ActivityRegisterBinding
import com.santisteban.mario.reportalo_ya.util.SharedPreferenceUtil

class RegisterActivity : BaseActivity<ActivityRegisterBinding>(ActivityRegisterBinding::inflate) {

    private lateinit var sharedPreferences: SharedPreferenceUtil

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPreferences = SharedPreferenceUtil().also {
            it.setSharedPreference(this)
        }

        binding.btnRegister.setOnClickListener {
            val userId = "1"
            val username = binding.edtUserName.text.toString()
            val email = binding.edtEmail.text.toString()
            val password = binding.edtPassword.text.toString()

            val user = User(
                userId,
                username,
                email,
                password
            )
            sharedPreferences.saveUser(user)
            startActivity(Intent(this, LoginActivity::class.java))

        }
        binding.btnGoLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }
}