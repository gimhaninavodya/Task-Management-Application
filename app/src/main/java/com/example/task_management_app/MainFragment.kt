package com.example.task_management_app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.task_management_app.database.ListDatabase
import com.example.task_management_app.databinding.ActivityMainFragmentBinding
import com.example.task_management_app.repository.ListRepository
import com.example.task_management_app.viewmodel.ListViewModel
import com.example.task_management_app.viewmodel.ListViewModelFactory

class MainFragment : AppCompatActivity() {

    private lateinit var binding: ActivityMainFragmentBinding
    lateinit var listViewModel: ListViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_fragment)

        setupViewModel()
    }

    private fun setupViewModel(){
        val listRepository = ListRepository(ListDatabase(this))
        val viewModelProviderFactory = ListViewModelFactory(application,listRepository)
        listViewModel = ViewModelProvider(this, viewModelProviderFactory)[ListViewModel ::class.java]
    }
}