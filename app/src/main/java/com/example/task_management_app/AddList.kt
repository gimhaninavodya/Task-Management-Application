package com.example.task_management_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class AddList : AppCompatActivity() {
    private lateinit var btnBack : ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_list)

        btnBack.setOnClickListener{
            val intent = Intent(this@AddList,ListMain::class.java )
            startActivity(intent)
        }
    }
}