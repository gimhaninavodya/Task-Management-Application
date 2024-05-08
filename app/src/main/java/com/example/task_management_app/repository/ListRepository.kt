package com.example.task_management_app.repository

import com.example.task_management_app.database.ListDatabase
import com.example.task_management_app.model.ListModel


class ListRepository(private val db: ListDatabase) {

    suspend fun insertList(list:ListModel) = db.getListDao().insertList(list)
    suspend fun deleteList(list:ListModel) = db.getListDao().deleteList(list)
    suspend fun updateList(list:ListModel) = db.getListDao().updateList(list)

    fun getAllList() = db.getListDao().getAllList()
    fun searchList(query: String?) = db.getListDao().searchList(query)
}