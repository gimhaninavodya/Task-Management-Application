package com.example.task_management_app.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.task_management_app.repository.ListRepository

class ListViewModelFactory (val app: Application,private val listRepository: ListRepository): ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ListViewModel(app, listRepository) as T
    }
}