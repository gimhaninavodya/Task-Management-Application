package com.example.task_management_app.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.task_management_app.model.ListModelNew
import com.example.task_management_app.repository.ListRepository
import kotlinx.coroutines.launch

class ListViewModel (app: Application, private val listRepository: ListRepository) : AndroidViewModel(app){
    fun addList(list: ListModelNew) =
        viewModelScope.launch {
            listRepository.insertList(list)
        }

    fun deleteList(list: ListModelNew) =
        viewModelScope.launch {
            listRepository.deleteList(list)
        }

    fun updateList(list: ListModelNew) =
        viewModelScope.launch {
            listRepository.updateList(list)
        }

    fun getAllList() = listRepository.getAllList()

    fun searchList(query: String?) =
        listRepository.searchList(query)

}