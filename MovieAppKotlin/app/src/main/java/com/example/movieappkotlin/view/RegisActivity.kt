package com.example.movieappkotlin.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.example.movieappkotlin.R
import com.example.movieappkotlin.viewmodel.RegisViewModel
import kotlinx.android.synthetic.main.activity_regis.*

class RegisActivity : AppCompatActivity() {

    private val regisViewModel: RegisViewModel by lazy {
        ViewModelProviders.of(this).get(RegisViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_regis)

        btn_cancel.setOnClickListener{
            val loginActivity = Intent(applicationContext, LoginActivity::class.java)
            startActivity(loginActivity)
        }

//        et_name.text.toString()
//        et_email.text.toString()
//        et_password.text.toString()
//        et_address.text.toString()
//        et_dob.text.toString()

        btn_register.setOnClickListener {
            regisViewModel.regis(et_name.text.toString(), et_email.text.toString(), et_password.text.toString(), et_address.text.toString(), et_dob.text.toString())
        }
    }
}