package com.mustafa.artbookhilttesting.repository

import com.mustafa.artbookhilttesting.api.ImageAPI
import com.mustafa.artbookhilttesting.model.ArtModel
import com.mustafa.artbookhilttesting.model.ImageModel
import com.mustafa.artbookhilttesting.util.Resource
import javax.inject.Inject

class ImageRepository @Inject constructor(
    private val api:ImageAPI
): IRepositoryImage  {
    override suspend fun getImageData(q:String): Resource<ImageModel> {
          try {
              val data = api.getData(q)
             data?.let {
                 return Resource.Success(data = data)
             }
          }catch(e:Exception) {
              return Resource.Error(e.message.toString())
          }
    }
}