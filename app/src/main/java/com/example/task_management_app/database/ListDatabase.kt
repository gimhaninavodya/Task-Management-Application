package com.example.task_management_app.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [List::class], version = 1)
abstract class ListDatabase: RoomDatabase() {

    abstract fun getListDao(): ListDao

    companion object{
        @Volatile
        private var instance: ListDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?:
        synchronized(LOCK){
            instance?:
            createDatabase(context).also{
                instance = it
            }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                ListDatabase::class.java,
                "list_db"
            ).build()


    }
}