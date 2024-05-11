package com.example.task_management_app.repository

import com.example.task_management_app.database.ListDatabase
import com.example.task_management_app.model.ListModelNew


class ListRepository(private val db: ListDatabase) {

    suspend fun insertList(list:ListModelNew) = db.getListDao().insertList(list)
    suspend fun deleteList(list:ListModelNew) = db.getListDao().deleteList(list)
    suspend fun updateList(list:ListModelNew) = db.getListDao().updateList(list)

    fun getAllList() = db.getListDao().getAllList()
    fun searchList(query: String?) = db.getListDao().searchList(query)
}