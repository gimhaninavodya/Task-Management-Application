package com.example.task_management_app.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.task_management_app.model.ListModelNew

@Dao
interface ListDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertList(list: ListModelNew)

    @Update
    suspend fun updateList(list: ListModelNew)

    @Delete
    suspend fun deleteList(list: ListModelNew)

    @Query("SELECT * FROM LIST ORDER BY id DESC")
    fun getAllList(): LiveData<List<ListModelNew>>

    @Query("SELECT * FROM LIST WHERE listTitle LIKE '%' || :query || '%'")
    fun searchList(query: String?): LiveData<List<ListModelNew>>
}
