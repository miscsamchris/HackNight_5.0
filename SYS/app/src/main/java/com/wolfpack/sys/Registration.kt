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
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T




class Registration : AppCompatActivity() {
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        val firebaseAuth = FirebaseAuth.getInstance();
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        database = FirebaseDatabase.getInstance().reference
        btnReg.setOnClickListener {
            firebaseAuth.createUserWithEmailAndPassword(user_name.text.toString(), password.text.toString()).addOnCompleteListener(this,OnCompleteListener {
                    if (!it.isSuccessful) {
                            Toast.makeText(
                                this.getApplicationContext(),
                                "SignUp unsuccessful: " + it.exception!!.message,
                                Toast.LENGTH_SHORT
                            ).show()

                        } else {
                        val values=HashMap<String, String>()
                        values.put("user_name",user_name.text.toString())
                        values.put("Aadhar Number",aadharNumber.text.toString())
                        values.put("address",address.text.toString())
                        database.child("/Users/").push().setValue(values)
                            startActivity(Intent(this, Login::class.java))
                        }
                })
        }

    }
}
