package com.wolfpack.sys

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.R.id.edit
import android.content.Intent
import android.content.SharedPreferences.Editor
import android.content.SharedPreferences
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
import android.widget.Toast
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.view.View


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
        dungeons.addAll(listOf("Ram's Storage","Oyo 2193 Manyata palace","Happy Residence","Backpacker panda"))
        val arrayAdapter = ArrayAdapter(this,android.R.layout.simple_list_item_1, dungeons.toList())
        list!!.adapter=arrayAdapter
        val arrayAdapter2 = ArrayAdapter(this,android.R.layout.simple_spinner_item, listOf("Bangalore","Chennai","Mysore"))
        arrayAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        Location!!.adapter=arrayAdapter2
        Location.setOnItemClickListener(object : OnItemClickListener {
            override fun onItemClick(arg0: AdapterView<*>, arg1: View, position: Int, arg3: Long) {
                val i=Intent(this@MainActivity,BookingPage::class.java)
                i.putExtra("id",position)
                startActivity(i)
            }
        })

    }

}
