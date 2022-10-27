package  com.aya.taskadva.data.remote

import com.aya.taskadva.domain.response.MainResponse

interface Apis {

    suspend fun allPhotos(): MainResponse?

}