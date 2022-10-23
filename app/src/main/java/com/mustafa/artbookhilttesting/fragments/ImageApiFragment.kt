package com.mustafa.artbookhilttesting.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.mustafa.artbookhilttesting.R
import com.mustafa.artbookhilttesting.adapter.ImageAdapter
import com.mustafa.artbookhilttesting.databinding.FragmentImageApiBinding
import com.mustafa.artbookhilttesting.viewmodel.ArtDetailViewModel
import com.mustafa.artbookhilttesting.viewmodel.ImageApiViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class ImageApiFragment
@Inject
constructor(
       private val imageAdapter: ImageAdapter
    ): Fragment(){

    private lateinit var binding: FragmentImageApiBinding

    private val viewmodel by viewModels<ImageApiViewModel>()
    private  val artDetailViewModel by viewModels<ArtDetailViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentImageApiBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        subscribeToObservers()

        var job:Job?=null
        binding.imageSearch.addTextChangedListener {
            job?.cancel()
            job = lifecycleScope.launch {
                delay(1000)
                it?.let {
                    if (it.toString().isNotEmpty()){
                        viewmodel.getImageData(it.toString())
                    }
                }
            }
        }

        binding.imageRecyclerView.adapter = imageAdapter
        binding.imageRecyclerView.layoutManager = GridLayoutManager(requireContext(),3)


       imageAdapter.setOnItemClickk{
            findNavController().popBackStack()
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            artDetailViewModel.setSelectedImage(url = it)
        }


    }

    private fun subscribeToObservers(){
        viewmodel.imageLiveList.observe(viewLifecycleOwner, Observer {
            it?.let {imageModel ->
                imageAdapter.imageList = imageModel.hits
            }
        })

        viewmodel.isLoading.observe(viewLifecycleOwner, Observer {
            if (it == true){
                binding.progress.visibility = View.VISIBLE
            }
            else{
                binding.progress.visibility = View.GONE
            }
        })

    }




}