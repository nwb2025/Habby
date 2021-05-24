package com.example.hubby.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "habits")
data class Habit
(
        @PrimaryKey val uid:String,
        val count: Int,
        val date: Int,
        val description: String,
        val frequency: Int,
        val priority: Int,
        val name: String,
        @ColumnInfo(name = "type") val type: Int,
        val done_dates:ArrayList<Int>,
        val color:Int
)