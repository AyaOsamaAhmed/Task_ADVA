package com.aya.taskadva.utils

import androidx.databinding.BindingAdapter
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import com.aya.taskadva.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target


object ExtensionView {

    @JvmStatic
    @BindingAdapter("app:bindImgUrl", "app:bindProgressItem")
     fun setGlideImageUrl( image: ImageView,url: String, progressBar: ProgressBar?){
        Glide.with(image.context)
            .load(url)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    progressBar?.visibility = View.GONE
                    image.setImageResource(R.mipmap.ic_launcher)
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    progressBar?.visibility = View.GONE
                    return false
                }


            })
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(image)
    }
}