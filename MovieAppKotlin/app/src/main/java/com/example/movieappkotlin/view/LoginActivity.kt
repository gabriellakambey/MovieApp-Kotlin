package com.example.movieappkotlin.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.movieappkotlin.R
import com.example.movieappkotlin.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private val loginViewModel: LoginViewModel by lazy {
        ViewModelProviders.of(this).get(LoginViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        tv_register.setOnClickListener{
            val register = Intent(applicationContext, RegisActivity::class.java)
            startActivity(register)
        }

        btn_login.setOnClickListener {
            loginViewModel.login(et_email.text.toString(), et_password.text.toString())
        }

        setObserver()
    }

    private fun setObserver() {
        loginViewModel.getLoginResponseModel().observe(this, Observer {
            if(it != null){
                val homeActivity = Intent(applicationContext, HomeActivity::class.java)
                startActivity(homeActivity)
                Toast.makeText(this, "Welcome!!", Toast.LENGTH_SHORT).show()
            }
        })

        loginViewModel.getErrorListener().observe(this, Observer {
            if(it){
                Toast.makeText(this, "Please check your email/password", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun login(email: String, password: String = "default"): String{
        return password
    }
}