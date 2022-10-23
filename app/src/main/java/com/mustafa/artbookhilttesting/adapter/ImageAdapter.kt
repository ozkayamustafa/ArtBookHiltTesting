package com.mustafa.artbookhilttesting.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.mustafa.artbookhilttesting.databinding.ImageRowBinding
import com.mustafa.artbookhilttesting.model.Hits
import javax.inject.Inject

class ImageAdapter
@Inject
constructor(
  private val glide:RequestManager
) : RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {

    private var  onItemClickListener:((String) -> Unit)? = null



    val diffUtil = object :DiffUtil.ItemCallback<Hits>(){
        override fun areItemsTheSame(oldItem: Hits, newItem: Hits): Boolean {
            return oldItem == newItem
        }
        override fun areContentsTheSame(oldItem: Hits, newItem: Hits): Boolean {
            return oldItem == newItem
        }
    }
    val recyclerListDiff = AsyncListDiffer(this,diffUtil)

     var imageList:List<Hits>
                    get() = recyclerListDiff.currentList
                    set(value) = recyclerListDiff.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val binding = ImageRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ImageViewHolder(binding = binding)
    }

    fun setOnItemClickk(listener:(String)->Unit){
        onItemClickListener = listener
    }



    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
            val url = imageList.get(position).previewURL.toString()
        holder.itemView.apply {
            glide.load(url).into(holder.binding.image)
            setOnClickListener {
                onItemClickListener?.let {
                    it(url)
                }
            }
        }
    }

    override fun getItemCount(): Int {
      return imageList.size
    }

    class ImageViewHolder(val binding: ImageRowBinding) : RecyclerView.ViewHolder(binding.root) {

    }


}