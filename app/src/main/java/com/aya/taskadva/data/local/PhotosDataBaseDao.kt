package com.aya.taskadva.data.local

import androidx.room.*

@Dao
interface PhotosDataBaseDao  {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
     suspend fun insertPhotos(news: TBPhotoModel)

    @Query("SELECT * from photo_table ORDER BY PhotoId ASC LIMIT :limit OFFSET :offset")
    suspend fun getAllPhotos(limit : Int , offset: Int ): List<TBPhotoModel>?

    @Query("SELECT COUNT(id) from photo_table")
    suspend fun getSize(): Int?


}