package com.aya.taskadva.presentation.adapter

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.aya.taskadva.data.local.PhotosDataBaseRepository
import com.aya.taskadva.data.local.TBPhotoModel

class PhotosPagingSource (private val repo: PhotosDataBaseRepository) :PagingSource<Int , TBPhotoModel>(){


    companion object {
        private const val STARTING_PAGE_INDEX = 1
    }

    override fun getRefreshKey(state: PagingState<Int, TBPhotoModel>): Int? {

        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TBPhotoModel> {
        val position = params.key ?: STARTING_PAGE_INDEX
        return try{

            Log.i("","")
            val model = repo.getAllPhotos(params.loadSize,position*params.loadSize)
            LoadResult.Page(
                data = model!!,
                prevKey = if(position ==0 ) null else position -1,
                nextKey = if(model.isEmpty()) null else position +1
            )
        }catch (e :Exception){
            LoadResult.Error(e)
        }
    }
}