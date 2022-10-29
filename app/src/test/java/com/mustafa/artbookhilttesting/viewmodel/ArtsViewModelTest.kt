package com.mustafa.artbookhilttesting.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.mustafa.artbookhilttesting.MainCoroutineRule
import com.mustafa.artbookhilttesting.getOrAwaitValue
import com.mustafa.artbookhilttesting.model.ArtModel
import com.mustafa.artbookhilttesting.repository.FakeRoomRepositoryTest
import com.mustafa.artbookhilttesting.util.Resource


import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ArtsViewModelTest {

    private lateinit var viewModel: ArtDetailViewModel


    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()


    @Before
    fun setUp() {
        viewModel = ArtDetailViewModel(FakeRoomRepositoryTest())
    }

    @After
    fun tearDown() {
    }

    @Test
    fun `insert art without year return erro`(){
           viewModel.makeArt("Mona Lisa","Da Vinci","")
           var value = viewModel.insertMessage.getOrAwaitValue()
            assertThat(value.message).isEqualTo("Enter name,artistName,year")
    }
    @Test
    fun `insert art without name return erro`(){
        viewModel.makeArt("","Da Vinci","1871")
        var value = viewModel.insertMessage.getOrAwaitValue()
        assertThat(value.message).isEqualTo("Enter name,artistName,year")
    }
    @Test
    fun `insert art without artist name return erro`(){
        viewModel.makeArt("Mona Lisa","","1871")
        var value = viewModel.insertMessage.getOrAwaitValue()
        assertThat(value.message).isEqualTo("Enter name,artistName,year")
    }
}