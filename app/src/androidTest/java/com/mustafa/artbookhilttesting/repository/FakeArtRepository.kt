package com.mustafa.artbookhilttesting.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mustafa.artbookhilttesting.model.ArtModel

class FakeArtRepository : IRoomRepository {

    private val arts = mutableListOf<ArtModel>()
    private val artsLiveData = MutableLiveData<List<ArtModel>>(arts)

    override suspend fun insert(artModel: ArtModel) {
       arts.add(artModel)
    }

    override fun getAllData(): LiveData<List<ArtModel>> {
       return artsLiveData
    }

    override suspend fun delete(artModel: ArtModel) {
       arts.remove(artModel)
    }
}