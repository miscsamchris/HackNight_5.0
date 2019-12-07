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
import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.FirebaseError
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*

class Dungeon(val name:String,val location:String,val rating:String)

class MainActivity : AppCompatActivity() {
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        database = FirebaseDatabase.getInstance().reference
        val pref = applicationContext.getSharedPreferences("Login", 0) // 0 - for private mode
        val editor = pref.edit()
        if (pref.getString("user_name", null)==null || pref.getString("password", null)==null) {
            var i = Intent(this, Login::class.java)
            startActivity(i)
        }
        var dungeons=ArrayList<String>()
        val menuListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                dataSnapshot.children.forEach {
                    var map=ArrayList<Dungeon>()

                    dataSnapshot.children.mapNotNullTo(map) { it.getValue<Dungeon>(Dungeon::class.java)
                    }
                    map.forEach {
                        dungeons.add(it.name)
                    }
                }
            }
            override fun onCancelled(databaseError: DatabaseError) {
                println("loadPost:onCancelled ${databaseError.toException()}")
            }
        }
        database.child("Dungeons/").addValueEventListener(menuListener)
        val arrayAdapter = ArrayAdapter(this,android.R.layout.simple_list_item_1, dungeons.toList())
        list!!.adapter=arrayAdapter
    }

}
