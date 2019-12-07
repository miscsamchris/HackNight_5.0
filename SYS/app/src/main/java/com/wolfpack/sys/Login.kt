package com.wolfpack.sys

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import androidx.annotation.NonNull
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T




class Login : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val firebaseAuth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_login)
        val pref = applicationContext.getSharedPreferences("Login", 0) // 0 - for private mode
        val editor = pref.edit()
        val authStateListener = FirebaseAuth.AuthStateListener { firebaseAuth ->
            val user = firebaseAuth.currentUser
            if (user != null) {
                Toast.makeText(this, "User logged in ", Toast.LENGTH_SHORT).show()
                val I = Intent(this, MainActivity::class.java)
                startActivity(I)
            } else {
                Toast.makeText(this, "Login to continue", Toast.LENGTH_SHORT).show()
            }
        }
        btnLogIn.setOnClickListener {
            if (user_name.text.isNotEmpty() && password.text.isNotEmpty()){

                firebaseAuth.signInWithEmailAndPassword(user_name.text.toString(),password.text.toString()).addOnCompleteListener(this, OnCompleteListener<AuthResult> { task ->
                    if(task.isSuccessful){
                        var intent = Intent(this, MainActivity::class.java)
                        editor.putString("user_name",user_name.text.toString())
                        editor.putString("password",password.text.toString())
                        editor.commit()
                        startActivity(intent)
                    }else{
                        Toast.makeText(this,"Enter correct details",Toast.LENGTH_SHORT).show()
                    }
            })
            }
            else{
                Toast.makeText(this,"Enter Username and details",Toast.LENGTH_SHORT).show()
            }
        }
        registration.setOnClickListener {
            startActivity(Intent(this,Registration::class.java))
        }
    }
}
