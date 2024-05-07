package com.example.task_management_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class MainActivity : AppCompatActivity() {
    private lateinit var mainBg : ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainBg.setOnClickListener{
            val intent = Intent(this@MainActivity,ListMain::class.java )
            startActivity(intent)
        }
    }
}