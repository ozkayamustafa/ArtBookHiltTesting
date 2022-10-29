package com.mustafa.artbookhilttesting.di

import android.content.Context
import androidx.room.Room
import com.mustafa.artbookhilttesting.roomdb.db.ArtDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModulTest {

    @Named("textDatabase")
    @Provides
    fun injectInMemoryRoom(@ApplicationContext context:Context)=
            Room.inMemoryDatabaseBuilder(context,ArtDatabase::class.java)
                .allowMainThreadQueries()
                .build()


}