package com.example.news_app.database

import androidx.room.TypeConverter
import com.example.news_app.models.Source

class ConvertersSource {

    @TypeConverter
    fun fromSource(source: Source) : String {
        return source.name
    }

    @TypeConverter
    fun toSource(name: String) : Source {
        return Source(name, name)
    }
}