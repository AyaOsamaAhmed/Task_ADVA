package  com.aya.taskadva.data.remote

import com.aya.taskadva.data.local.TBPhotoModel
import com.aya.taskadva.domain.model.SourceModel
import com.aya.taskadva.domain.response.MainResponse

interface Apis {

    suspend fun allPhotos(): ArrayList<TBPhotoModel>?

}