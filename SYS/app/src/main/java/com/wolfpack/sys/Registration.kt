package com.wolfpack.sys

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.NonNull
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import kotlinx.android.synthetic.main.activity_registration.*
import android.content.Intent
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T



class Registration : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        val firebaseAuth = FirebaseAuth.getInstance();
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        btnReg.setOnClickListener {
            firebaseAuth.createUserWithEmailAndPassword(user_name.text.toString(), password.text.toString()).addOnCompleteListener(this,OnCompleteListener {
                    if (!it.isSuccessful) {
                            Toast.makeText(
                                this.getApplicationContext(),
                                "SignUp unsuccessful: " + it.exception!!.message,
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            startActivity(Intent(this, Login::class.java))
                        }
                })
        }

    }
}
