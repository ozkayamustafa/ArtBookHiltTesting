package com.mustafa.artbookhilttesting.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.mustafa.artbookhilttesting.databinding.ArtRowBinding
import com.mustafa.artbookhilttesting.model.ArtModel
import javax.inject.Inject

class ArtAdapter
    @Inject
    constructor(
        private val glide:RequestManager
    )
    : RecyclerView.Adapter<ArtAdapter.ArtViewHoldder>() {

    private val diffUtil = object:DiffUtil.ItemCallback<ArtModel>(){
        override fun areItemsTheSame(oldItem: ArtModel, newItem: ArtModel): Boolean {
            return oldItem == newItem
        }
        override fun areContentsTheSame(oldItem: ArtModel, newItem: ArtModel): Boolean {
           return  oldItem == newItem
        }
    }
    private val recyclerListDiff = AsyncListDiffer(this,diffUtil)

    var artList:List<ArtModel>
                get() = recyclerListDiff.currentList
                set(value) = recyclerListDiff.submitList(value)



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtViewHoldder {
       val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ArtRowBinding.inflate(layoutInflater,parent,false)
        return ArtViewHoldder(binding)
    }

    override fun onBindViewHolder(holder: ArtViewHoldder, position: Int) {
        val art = artList[position]
        holder.binding.apply {
            artName.text = "Name: ${art.artName}"
            artArtist.text = "Artist Name: ${art.artArtist}"
            artYear.text = "Year: ${art.artYear}"
            glide.load(art.artImage).into(artImage)
        }
    }

    override fun getItemCount(): Int {
        return artList.size
    }

    class ArtViewHoldder(val binding: ArtRowBinding) : RecyclerView.ViewHolder(binding.root) {
    }

}