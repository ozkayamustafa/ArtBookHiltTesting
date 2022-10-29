package com.mustafa.artbookhilttesting.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mustafa.artbookhilttesting.model.ArtModel
import com.mustafa.artbookhilttesting.repository.IRoomRepository
import com.mustafa.artbookhilttesting.repository.RoomRepository
import com.mustafa.artbookhilttesting.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArtDetailViewModel
@Inject
constructor(
private val roomRepository: IRoomRepository
):ViewModel()
{

    private var insertArtMsg = MutableLiveData<Resource<ArtModel>>()
    val insertMessage:LiveData<Resource<ArtModel>>
                get() = insertArtMsg

    fun  resetInsertMessage(){
        insertArtMsg = MutableLiveData<Resource<ArtModel>>()
    }


    private val selectedImage= MutableLiveData<String>()
    val selectedImageLive:LiveData<String>
            get() = selectedImage


    fun setSelectedImage(url:String){
        selectedImage.value = url
    }

    fun insertArt(artModel: ArtModel){
        viewModelScope.launch {
            roomRepository.insert(artModel = artModel)
        }
    }


    fun makeArt(name:String,artistName:String,year:String){
        if (name.isEmpty() ||artistName.isEmpty() || year.isEmpty()){
            insertArtMsg.value = Resource.Error("Enter name,artistName,year",null)
            return
        }
        val art = ArtModel(artName = name, artArtist = artistName, artImage = selectedImage.value?:"", artYear = year)
        insertArt(art)
        setSelectedImage("")
        insertArtMsg.value = Resource.Success(art)
    }


}