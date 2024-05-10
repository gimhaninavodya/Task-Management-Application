package com.example.task_management_app

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var mainBg : ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainBg = findViewById(R.id.mainBg)
        mainBg.setOnClickListener{
            val intent = Intent(this@MainActivity, MainFragment::class.java )
            startActivity(intent)
        }
    }
}