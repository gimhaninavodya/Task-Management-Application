package com.example.task_management_app.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.util.Date

@Entity(tableName = "list")
@Parcelize
data class ListModelNew(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val listTitle: String,
    val listCategory: String,
    val listDes: String
): Parcelable
