package com.aya.taskadva.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "photo_table")
data class TBPhotoModel (
    @PrimaryKey(autoGenerate = true)
    var PhotoId : Int = 0 ,

    val albumId : Int,
    val id : Int,
    val title : String ,
    val url : String ,
    val thumbnailUrl : String

)