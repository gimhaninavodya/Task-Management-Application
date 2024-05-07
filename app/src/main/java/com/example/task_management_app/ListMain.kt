package com.example.task_management_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class ListMain : AppCompatActivity() {
    private lateinit var addBtn : ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_main)

        addBtn.setOnClickListener{
            val intent = Intent(this@ListMain,AddList::class.java )
            startActivity(intent)
        }
    }
}