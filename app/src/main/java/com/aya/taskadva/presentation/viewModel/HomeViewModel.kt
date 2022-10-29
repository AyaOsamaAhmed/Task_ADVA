package com.aya.taskadva.presentation.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aya.taskadva.data.local.PhotoDataBase
import com.aya.taskadva.data.local.PhotosDataBaseRepository
import com.aya.taskadva.data.local.TBPhotoModel
import com.aya.taskadva.data.remote.Apis
import com.aya.taskadva.domain.repositories.MainRepo
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    var requestDataLiveData = MutableLiveData<Any>()
   private lateinit var repository : PhotosDataBaseRepository

    val coroutineExceptionHandler = CoroutineExceptionHandler{_, throwable ->
        throwable.printStackTrace()
    }

    lateinit var retroService: Apis

    init {
     //   retroService = KtorClient.getInstance
    }

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


   /* fun getListData(): Flow<PagingData<SourceModel>> {
        return Pager (config = PagingConfig(pageSize = 10, maxSize = 200),
            pagingSourceFactory = {ModelPagingSource(retroService)}).flow.cachedIn(viewModelScope)
    }*/
    fun getList(){
        viewModelScope.launch(Dispatchers.IO ) {
            requestDataLiveData.postValue(MainRepo.allPhotos())
        }
    }




}