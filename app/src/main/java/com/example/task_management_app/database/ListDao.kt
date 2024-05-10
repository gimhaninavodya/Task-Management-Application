package com.example.task_management_app.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.task_management_app.model.ListModel

@Dao
interface ListDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertList(list: ListModel)

    @Update
    suspend fun updateList(list: ListModel)

    @Delete
    suspend fun deleteList(list: ListModel)

    @Query("SELECT * FROM LIST ORDER BY id DESC")
    fun getAllList(): LiveData<List<ListModel>>

    @Query("SELECT * FROM LIST WHERE listTitle LIKE :query OR listDes LIKE :query")
    fun searchList(query: String?): LiveData<List<ListModel>>
}
