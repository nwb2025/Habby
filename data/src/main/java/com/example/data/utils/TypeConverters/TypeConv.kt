package com.example.data.utils.TypeConverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class TypeConv {
    @TypeConverter
    fun fromString(value:String?) : ArrayList<Int> {
        val listType = object : TypeToken<ArrayList<Int>>(){}.type
        return Gson().fromJson(value, listType )
    }

    @TypeConverter
    fun fromArrayList(list:ArrayList<Int?>) : String{
        return Gson().toJson(list)

    }

}