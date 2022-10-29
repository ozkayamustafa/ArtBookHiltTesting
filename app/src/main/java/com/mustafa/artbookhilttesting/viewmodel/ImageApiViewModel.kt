package com.mustafa.artbookhilttesting.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mustafa.artbookhilttesting.model.ImageModel
import com.mustafa.artbookhilttesting.repository.IRepositoryImage
import com.mustafa.artbookhilttesting.repository.ImageRepository
import com.mustafa.artbookhilttesting.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ImageApiViewModel
@Inject
constructor(
    private val imageRepository: IRepositoryImage
):ViewModel()
{

  private  val imageList = MutableLiveData<ImageModel>()
    val imageLiveList:LiveData<ImageModel>
                get() = imageList

    private val loading = MutableLiveData<Boolean>()
    val isLoading:LiveData<Boolean>
            get() = loading

    init {
        loading.value = false
    }


    fun getImageData(q:String){
        loading.value = true
        viewModelScope.launch {
            val result = imageRepository.getImageData(q = q)
            when(result){
               is Resource.Success ->{
                    val imageData = result.data
                   imageData?.let {
                       imageList.value = it
                       loading.value = false
                   }
                }
                is Resource.Error ->{
                    loading.value = false
                }
            }


        }

    }


}