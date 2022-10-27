package com.aya.taskadva.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.aya.taskadva.databinding.HomeFragmentBinding


class HomeFragment : Fragment()  {

    private lateinit var binding : HomeFragmentBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = HomeFragmentBinding.inflate(inflater , container , false)



        return binding.root
    }


}