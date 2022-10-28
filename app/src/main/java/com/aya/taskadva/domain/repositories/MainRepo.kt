package com.aya.taskadva.domain.repositories

import io.ktor.client.features.*
import android.util.Log
import com.aya.taskadva.data.remote.ApisServicesImpl
import com.aya.taskadva.domain.model.SourceModel
import com.aya.taskadva.domain.response.MainResponse

object MainRepo {

    suspend fun allPhotos(): ArrayList<SourceModel>?  {
        return   try {

           ApisServicesImpl.allPhotos()

        } catch (e:  ClientRequestException) {
            Log.d("MainRepo", "Response exception ${e.message}")
            null
        }

    }


}