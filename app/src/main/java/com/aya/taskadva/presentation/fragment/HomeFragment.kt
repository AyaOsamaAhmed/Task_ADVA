package com.aya.taskadva.presentation.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.aya.taskadva.R
import com.aya.taskadva.databinding.HomeFragmentBinding
import com.aya.taskadva.domain.model.SourceModel
import com.aya.taskadva.presentation.adapter.PhotoAdapter
import com.aya.taskadva.presentation.interfaces.onClick
import com.aya.taskadva.presentation.viewModel.HomeViewModel

import com.aya.taskadva.presentation.activity.MainActivity

import android.content.Intent
import android.media.Image
import androidx.lifecycle.lifecycleScope
import com.aya.taskadva.data.local.PhotoDataBase
import com.aya.taskadva.data.local.TBPhotoModel
import com.bumptech.glide.Glide
import com.stfalcon.imageviewer.StfalconImageViewer
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class HomeFragment : Fragment() , onClick {

    private lateinit var binding : HomeFragmentBinding
    private lateinit var viewModel : HomeViewModel

    private lateinit var adapter : PhotoAdapter
    private lateinit var photoDataBase: PhotoDataBase

   private val mainActivity  by lazy { activity as MainActivity }

    private val navController by lazy {
        val navHostFragment = activity?.supportFragmentManager
            ?.findFragmentById(R.id.homeframelayout) as NavHostFragment
        navHostFragment.navController
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = HomeFragmentBinding.inflate(inflater , container , false)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        photoDataBase = PhotoDataBase.getInstance(requireContext())

        adapter = PhotoAdapter(this)
        lifecycleScope.launch {
         if( photoDataBase.photosDataBaseDao.getSize() == 0 )
             viewModel.getList()

            Log.d("HomeFragment", "get data")
        }

        viewModel.requestDataLiveData.observe(viewLifecycleOwner, Observer {
            val data = it as ArrayList<TBPhotoModel>
            Log.d("HomeFragment", "data size:${data.size}")
            viewModel.setInsertPhoto(photoDataBase , data )
         //   adapter = PhotoAdapter(this)
        //    adapter.submitData(data)
        //    binding.recyclerPhoto.setAdapter(adapter)
        })

        lifecycleScope.launch{
            viewModel.getDBListData(photoDataBase).collectLatest {
                adapter.submitData(it)
                binding.recyclerPhoto.setAdapter(adapter)
            }
        }


        return binding.root
    }

    override fun onClickPhoto(photoUrl: String) {
        val images: MutableList<String> = ArrayList()
        images.add(photoUrl.plus(".jpg"))
        StfalconImageViewer.Builder<String>(context, images) { view, image ->
           // Picasso.get().load(image.url).into(view)
            Glide.with(requireContext()).load(image).into(view)
    }.show()


    }


}