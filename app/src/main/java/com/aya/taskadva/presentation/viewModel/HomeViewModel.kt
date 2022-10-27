package com.aya.taskadva.presentation.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aya.taskadva.domain.repositories.MainRepo
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    var requestDataLiveData = MutableLiveData<Any>()

    init {
        getList()
    }

    val coroutineExceptionHandler = CoroutineExceptionHandler{_, throwable ->
        throwable.printStackTrace()
    }

    fun getList(){
        viewModelScope.launch(Dispatchers.IO ) {
            requestDataLiveData.postValue(MainRepo.allPhotos())
        }
    }




}