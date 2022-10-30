package com.aya.taskadva.presentation.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.aya.taskadva.data.local.PhotoDataBase
import com.aya.taskadva.data.local.PhotosDataBaseRepository
import com.aya.taskadva.data.local.TBPhotoModel
import com.aya.taskadva.data.remote.Apis
import com.aya.taskadva.domain.repositories.MainRepo
import com.aya.taskadva.presentation.adapter.PhotosPagingSource
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    var requestDataLiveData = MutableLiveData<Any>()
   private lateinit var repository : PhotosDataBaseRepository

    val coroutineExceptionHandler = CoroutineExceptionHandler{_, throwable ->
        throwable.printStackTrace()
    }

    lateinit var retroDB: PhotosDataBaseRepository



    fun setInsertPhoto(instance: PhotoDataBase,models:ArrayList<TBPhotoModel>){
        repository = PhotosDataBaseRepository( instance.photosDataBaseDao)

        viewModelScope.launch(Dispatchers.IO ) {
           val size  = repository.checkSize()
           Log.d("HomeViewModel", "database size:${size}")
            if(size == 0 ) {
                repeat(models.size) { it ->
                    repository.insert(models[it])
                }
            }
        }
    }


    fun getDBListData(instance: PhotoDataBase): Flow<PagingData<TBPhotoModel>> {
        retroDB = PhotosDataBaseRepository(instance.photosDataBaseDao)
        return Pager(PagingConfig (pageSize = 10, maxSize = 5000)){
             PhotosPagingSource(retroDB)}.flow.cachedIn(viewModelScope)
    }


    fun getList(){
        viewModelScope.launch(Dispatchers.IO ) {
            requestDataLiveData.postValue(MainRepo.allPhotos())
        }
    }




}