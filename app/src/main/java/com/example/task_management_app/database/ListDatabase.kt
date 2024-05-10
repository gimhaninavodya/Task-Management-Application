package com.example.task_management_app.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.task_management_app.model.ListModel

@Database(entities = [ListModel::class], version = 1)
@TypeConverters(Converters::class) // Add this line to register type converters
abstract class ListDatabase : RoomDatabase() {

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