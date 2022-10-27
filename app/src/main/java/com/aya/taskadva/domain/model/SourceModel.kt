package com.aya.taskadva.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class SourceModel (
    val albumId : Int,
    val id : Int,
    val title : String ,
    val url : String ,
    val thumbnailUrl : String

)