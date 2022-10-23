package com.mustafa.artbookhilttesting.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mustafa.artbookhilttesting.adapter.ArtAdapter
import com.mustafa.artbookhilttesting.databinding.FragmentArtsBinding
import com.mustafa.artbookhilttesting.viewmodel.ArtsViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ArtsFragment
    @Inject
    constructor(
        val adapter: ArtAdapter
    ): Fragment() {

    private lateinit var binding: FragmentArtsBinding

    private val artViewmodel: ArtsViewModel by viewModels<ArtsViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentArtsBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fab.setOnClickListener {
            val action = ArtsFragmentDirections.actionArtsFragmentToArtDetailsFragment()
            Navigation.findNavController(it).navigate(action)
        }
        subscribeToObservers()
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        ItemTouchHelper(swipeCall).attachToRecyclerView(binding.recyclerView)

    }
    private fun subscribeToObservers(){
        artViewmodel.allData.observe(viewLifecycleOwner, Observer {
            it?.let {
                if (it.isNotEmpty()){
                    adapter.artList = it
                }else{
                    adapter.artList = arrayListOf()
                }
            }
        })
    }

    private val swipeCall = object:ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT){
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            return true
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val position = viewHolder.layoutPosition
            val selectedArt = adapter.artList[position]
            artViewmodel.deleteArt(selectedArt)
        }

    }

}