package com.wolfpack.sys

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.R.id.edit
import android.content.Intent
import android.content.SharedPreferences.Editor
import android.content.SharedPreferences
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T



class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val pref = applicationContext.getSharedPreferences("Login", 0) // 0 - for private mode
        val editor = pref.edit()
        if (pref.getString("user_name", null)==null || pref.getString("password", null)==null){
            var i= Intent(this,Login::class.java)
            startActivity(i)
        }
    }
}
