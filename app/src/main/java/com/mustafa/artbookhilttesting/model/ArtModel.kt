package com.mustafa.artbookhilttesting.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ArtModel(
    val artImage:String,
    val artName:String,
    val artArtist:String,
    val artYear:String,
    @PrimaryKey(autoGenerate = true)
    val id:Int? = null
)