package com.mustafa.artbookhilttesting.roomdb.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mustafa.artbookhilttesting.model.ArtModel

@Dao
interface ArtDao {

    @Query("SELECT * FROM ArtModel")
    fun observeData():LiveData<List<ArtModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArt(artModel: ArtModel)

    @Delete
    suspend fun delete(artModel: ArtModel)


}