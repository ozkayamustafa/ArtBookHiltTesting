package com.mustafa.artbookhilttesting.roomdb.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mustafa.artbookhilttesting.model.ArtModel
import com.mustafa.artbookhilttesting.roomdb.dao.ArtDao

@Database(entities = arrayOf(ArtModel::class), version = 1)
abstract class ArtDatabase: RoomDatabase() {
    abstract fun artDao():ArtDao
}