package com.mustafa.artbookhilttesting.roomdb

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.mustafa.artbookhilttesting.getOrAwaitValue
import com.mustafa.artbookhilttesting.model.ArtModel
import com.mustafa.artbookhilttesting.roomdb.dao.ArtDao
import com.mustafa.artbookhilttesting.roomdb.db.ArtDatabase
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named

@SmallTest
@ExperimentalCoroutinesApi
@HiltAndroidTest
class ArtDaoTest {

    @get:Rule
    var instansTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    @Named("textDatabase")
    lateinit var artDatabase: ArtDatabase


    private lateinit var  artDao: ArtDao

    @Before
    fun setup(){

        hiltRule.inject()

      /*  artDatabase = Room.inMemoryDatabaseBuilder(ApplicationProvider.getApplicationContext(),ArtDatabase::class.java)
            .allowMainThreadQueries()
            .build()

       */

        artDao = artDatabase.artDao()
    }


    @After
    fun teardown(){
        artDatabase.close()
    }

    @Test
    fun insertArtTesting() = runBlocking{
                    val exampleArt = ArtModel("test.com","Mona Lisa","Da Vinci","1700",1)
                    artDao.insertArt(exampleArt)
            val list = artDao.observeData().getOrAwaitValue()
                assertThat(list).contains(exampleArt)
    }

    @Test
    fun deleteArtTesting() = runBlocking{
        val exampleArt = ArtModel("test.com","Mona Lisa","Da Vinci","1700",1)
        artDao.insertArt(exampleArt)
        artDao.delete(exampleArt)
        val list = artDao.observeData().getOrAwaitValue()
        assertThat(list).doesNotContain(exampleArt)

    }


}