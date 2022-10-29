package com.mustafa.artbookhilttesting.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.RequestManager
import com.mustafa.artbookhilttesting.R
import com.mustafa.artbookhilttesting.databinding.FragmentArtDetailsBinding
import com.mustafa.artbookhilttesting.listener.IUrlImage
import com.mustafa.artbookhilttesting.util.Resource
import com.mustafa.artbookhilttesting.viewmodel.ArtDetailViewModel
import com.mustafa.artbookhilttesting.viewmodel.ImageApiViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ArtDetailsFragment
    @Inject
    constructor(
        private val glide: RequestManager
    )
    : Fragment() {
    private lateinit var binding: FragmentArtDetailsBinding

      lateinit var viewmodell:ArtDetailViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentArtDetailsBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewmodell = ViewModelProvider(requireActivity()).get(ArtDetailViewModel::class.java)
      /*  val urlImage =  findNavController().currentBackStackEntry?.savedStateHandle?.get<String>("url").toString()
        viewmodell.setSelectedImage(url = urlImage)
*/

        binding.addImage.setOnClickListener {
            val action = ArtDetailsFragmentDirections.actionArtDetailsFragmentToImageApiFragment()
            findNavController().navigate(action)
        }

        subscribeToObservers()
        // Geri basıldığında ne yapılacak işlemi burada yapabiliriz
        val callBack = object:OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                findNavController().popBackStack()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(callBack)


        binding.btnSave.setOnClickListener {
                    viewmodell.makeArt(binding.editArtNamee.text.toString(),binding.editArtNamee.text.toString(),binding.editArtYear.text.toString())
        }



    }

    private fun subscribeToObservers(){

        viewmodell.selectedImageLive.observe(viewLifecycleOwner, Observer {

              it?.let { url->
                  glide.load(url).into(binding.addImage)
              }

        })
        viewmodell.insertMessage.observe(viewLifecycleOwner, Observer {
               it?.let {
                   when(it){
                       is Resource.Success ->{
                           Toast.makeText(requireContext(), "Success", Toast.LENGTH_SHORT).show()
                           findNavController().popBackStack()
                           viewmodell.resetInsertMessage()
                       }
                       is Resource.Error ->{
                           Toast.makeText(requireContext(), it.message?:"Error", Toast.LENGTH_SHORT).show()
                       }
                   }
               }
            })

    }


}