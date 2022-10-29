package com.aya.taskadva.data.remote

import com.aya.taskadva.data.local.TBPhotoModel
import com.aya.taskadva.domain.model.SourceModel
import com.aya.taskadva.domain.response.MainResponse
import io.ktor.client.request.*

object ApisServicesImpl : Apis {

    private val httpClient by lazy {
        KtorClient.getInstance
    }

    override suspend fun allPhotos(): ArrayList<TBPhotoModel>? {
      return httpClient.get(  path = "photos")
    }

}