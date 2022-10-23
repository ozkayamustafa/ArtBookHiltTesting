package com.mustafa.artbookhilttesting.model

import com.google.gson.annotations.SerializedName


data class ImageModel(

    @SerializedName("total"     ) var total     : Int?            = null,
    @SerializedName("totalHits" ) var totalHits : Int?            = null,
    @SerializedName("hits"      ) var hits      : ArrayList<Hits> = arrayListOf()

)
