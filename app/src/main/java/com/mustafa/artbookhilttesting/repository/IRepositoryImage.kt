package com.mustafa.artbookhilttesting.repository

import com.mustafa.artbookhilttesting.model.ArtModel
import com.mustafa.artbookhilttesting.model.ImageModel
import com.mustafa.artbookhilttesting.util.Resource

interface IRepositoryImage {
    suspend fun getImageData(q:String):Resource<ImageModel>
}