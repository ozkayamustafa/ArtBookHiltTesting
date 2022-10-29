package com.mustafa.artbookhilttesting.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mustafa.artbookhilttesting.model.ArtModel
import org.junit.Assert.*

class FakeRoomRepositoryTest:IRoomRepository {
    private val arts = mutableListOf<ArtModel>()
    private val artsLiveData = MutableLiveData<List<ArtModel>>(arts)

    override suspend fun insert(artModel: ArtModel) {
        arts.add(artModel)
        refleshData()
    }

    override fun getAllData(): LiveData<List<ArtModel>> {
        return artsLiveData
    }

    override suspend fun delete(artModel: ArtModel) {
        arts.remove(artModel)
        refleshData()
    }

    private fun refleshData(){
        artsLiveData.postValue(arts)
    }
}