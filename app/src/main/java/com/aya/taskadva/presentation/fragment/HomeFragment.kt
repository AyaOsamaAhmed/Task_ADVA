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
import com.aya.taskadva.domain.response.MainResponse
import com.aya.taskadva.presentation.activity.MainActivity
import com.aya.taskadva.presentation.viewModel.HomeViewModel


class HomeFragment : Fragment()  {

    private lateinit var binding : HomeFragmentBinding
    private lateinit var viewModel : HomeViewModel

    val mainActivity  by lazy { activity as MainActivity }

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

        viewModel.requestDataLiveData.observe(viewLifecycleOwner, Observer {
            val data = it as ArrayList<SourceModel>
            Log.d("HomeFragment", "data size:${data.size}")
        })


        return binding.root
    }


}