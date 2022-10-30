package com.aya.taskadva.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.PagerAdapter
import com.aya.taskadva.R
import com.aya.taskadva.BR
import com.aya.taskadva.data.local.TBPhotoModel
import com.aya.taskadva.databinding.ItemPhotoBinding
import com.aya.taskadva.domain.model.SourceModel
import com.aya.taskadva.presentation.interfaces.onClick

class PhotoAdapter (val action : onClick) : PagingDataAdapter<TBPhotoModel, PhotoAdapter.subViewHolder>(DiffUtilCallBack()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): subViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemPhotoBinding.inflate(layoutInflater, parent, false)
        return  subViewHolder(binding)
    }

    override fun onBindViewHolder(holder: subViewHolder, position: Int) {
        holder.bind(getItem(position)!!)

    }

    class DiffUtilCallBack: DiffUtil.ItemCallback<TBPhotoModel>() {
        override fun areItemsTheSame(oldItem: TBPhotoModel, newItem: TBPhotoModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: TBPhotoModel, newItem: TBPhotoModel): Boolean {
            return oldItem == newItem

        }

    }

    class subViewHolder(binding: ItemPhotoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var itemRowBinding: ItemPhotoBinding = binding
        fun bind(obj: Any?) {
            itemRowBinding.setVariable(BR.model, obj)
            itemRowBinding.executePendingBindings()
        }
    }



}