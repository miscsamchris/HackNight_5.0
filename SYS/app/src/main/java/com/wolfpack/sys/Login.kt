package com.wolfpack.sys

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*

class Login : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val pref = applicationContext.getSharedPreferences("Login", 0) // 0 - for private mode
        val editor = pref.edit()
        btnLogIn.setOnClickListener {
            if (user_name.text.length>5 && password.text.length>5){
                editor.putString("user_name",user_name.text.toString())
                editor.putString("password",password.text.toString())
                startActivity(Intent(this,MainActivity::class.java))
            }
            else{
                Toast.makeText(this,"Enter correct details",Toast.LENGTH_SHORT).show()
            }
        }
    }
}
