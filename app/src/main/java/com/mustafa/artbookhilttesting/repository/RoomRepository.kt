package com.mustafa.artbookhilttesting.repository

import androidx.lifecycle.LiveData
import com.mustafa.artbookhilttesting.model.ArtModel
import com.mustafa.artbookhilttesting.roomdb.dao.ArtDao
import javax.inject.Inject

class RoomRepository
@Inject constructor( private  val  artDao: ArtDao): IRoomRepository
{
    override suspend fun insert(artModel: ArtModel) {
         artDao.insertArt(artModel = artModel)
    }

    override fun getAllData(): LiveData<List<ArtModel>> {
        val data = artDao.observeData()
        return data
    }

    override suspend fun delete(artModel: ArtModel) {
        artDao.delete(artModel = artModel)
    }

}