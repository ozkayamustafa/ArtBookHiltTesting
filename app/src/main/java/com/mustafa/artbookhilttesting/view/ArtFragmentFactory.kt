package com.mustafa.artbookhilttesting.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.bumptech.glide.RequestManager
import com.mustafa.artbookhilttesting.adapter.ArtAdapter
import com.mustafa.artbookhilttesting.adapter.ImageAdapter
import com.mustafa.artbookhilttesting.fragments.ArtDetailsFragment
import com.mustafa.artbookhilttesting.fragments.ArtsFragment
import com.mustafa.artbookhilttesting.fragments.ImageApiFragment
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

class ArtFragmentFactory
@Inject
constructor(
    private val artAdapter: ArtAdapter,
    private val glide:RequestManager,
    private val imageAdapter: ImageAdapter
):FragmentFactory()
{
    override fun instantiate(classLoader: ClassLoader, className: String): Fragment{
            return  when(className){
                ArtsFragment::class.java.name-> ArtsFragment(adapter = artAdapter)
                ArtDetailsFragment::class.java.name-> ArtDetailsFragment(glide)
                ImageApiFragment::class.java.name->ImageApiFragment(imageAdapter)
                else -> super.instantiate(classLoader, className)
            }

    }


}