package com.mustafa.artbookhilttesting.di

import android.content.Context
import androidx.room.Room
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.mustafa.artbookhilttesting.R
import com.mustafa.artbookhilttesting.api.ImageAPI
import com.mustafa.artbookhilttesting.repository.IRepositoryImage
import com.mustafa.artbookhilttesting.repository.IRoomRepository
import com.mustafa.artbookhilttesting.repository.ImageRepository
import com.mustafa.artbookhilttesting.repository.RoomRepository
import com.mustafa.artbookhilttesting.roomdb.dao.ArtDao
import com.mustafa.artbookhilttesting.roomdb.db.ArtDatabase
import com.mustafa.artbookhilttesting.util.Constans
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class Modul {

    @Singleton
    @Provides
    fun providesRepository(api:ImageAPI):IRepositoryImage{
        return  ImageRepository(api = api)
    }

    @Singleton
    @Provides
    fun providesRoomRepository(artDao: ArtDao):IRoomRepository{
        return RoomRepository(artDao = artDao)
    }

    @Singleton
    @Provides
    fun providesRetrofit():ImageAPI{
        return Retrofit.Builder()
            .baseUrl(Constans.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ImageAPI::class.java)
    }


    @Singleton
    @Provides
    fun providesRoom(@ApplicationContext context: Context):ArtDao{
        return Room
            .databaseBuilder(
                context,
                ArtDatabase::class.java,
                "ARTS")
            .build().artDao()
    }

    @Singleton
    @Provides
    fun providesRequestGlide(@ApplicationContext context: Context):RequestManager{
        return Glide.with(context)
            .setDefaultRequestOptions(
                RequestOptions().placeholder(R.drawable.ic_launcher_foreground)
                    .error(R.drawable.ic_launcher_foreground)
            )
    }

}