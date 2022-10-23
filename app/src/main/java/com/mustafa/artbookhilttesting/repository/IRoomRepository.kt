package com.mustafa.artbookhilttesting.repository

import androidx.lifecycle.LiveData
import com.mustafa.artbookhilttesting.model.ArtModel

interface IRoomRepository {

    suspend fun insert(artModel: ArtModel)
     fun getAllData():LiveData<List<ArtModel>>
    suspend fun delete(artModel: ArtModel)

}