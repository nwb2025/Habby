package com.example.domain.models

data  class HabitDomainLayer(
    val uid:String?,
    val count: Int,
    val date: Int,
    val description: String,
    val frequency: Int,
    val priority: Int,
    val name: String,
    val type: Int,
    val color : Int,
    val done_dates : ArrayList<Int>
)