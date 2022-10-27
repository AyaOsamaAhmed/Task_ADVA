package com.aya.taskadva.domain.response

import com.aya.taskadva.domain.model.SourceModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MainResponse (

    @SerialName("data")
    val data: ArrayList<SourceModel>



)
