package com.example.data.models


data class HabitResponse
(
    val uid:String?,
    val color:Int,
    val count: Int,
    val date: Int,
    val description: String,
    val done_dates:ArrayList<Int>,
    val frequency: Int,
    val priority: Int,
    val title:String,
    val type:Int
)