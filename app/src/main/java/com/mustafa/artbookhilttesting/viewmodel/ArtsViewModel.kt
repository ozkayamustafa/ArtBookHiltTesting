package com.mustafa.artbookhilttesting.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mustafa.artbookhilttesting.model.ArtModel
import com.mustafa.artbookhilttesting.repository.RoomRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArtsViewModel
@Inject
constructor(
    private val roomRepository: RoomRepository
):ViewModel()
{

    val allData = roomRepository.getAllData()

    fun deleteArt(artModel: ArtModel){
        viewModelScope.launch {
            roomRepository.delete(artModel = artModel)
        }
    }



}