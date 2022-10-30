package com.aya.taskadva.data.local

import androidx.annotation.WorkerThread

class PhotosDataBaseRepository(val photosDao : PhotosDataBaseDao) {


    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun getAllPhotos(limit : Int , offset: Int):List<TBPhotoModel>? {
       return photosDao.getAllPhotos(limit , offset)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun checkSize():Int{
      return  photosDao.getSize()?: 0
    }


    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(model: TBPhotoModel){
        photosDao.insertPhotos(model)
    }



}