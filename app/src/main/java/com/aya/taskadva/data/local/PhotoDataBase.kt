package com.aya.taskadva.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [TBPhotoModel::class], version = 1,  exportSchema = false)
abstract class PhotoDataBase : RoomDatabase() {

    abstract val photosDataBaseDao : PhotosDataBaseDao

    companion object {

        @Volatile
        private var INSTANCE: PhotoDataBase? = null

        fun getInstance(context: Context): PhotoDataBase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        PhotoDataBase::class.java,
                        "photos_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }

}
}