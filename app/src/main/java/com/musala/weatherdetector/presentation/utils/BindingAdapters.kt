package com.musala.weatherdetector.presentation.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.musala.weatherdetector.R

@BindingAdapter("imageUrl")
fun ImageView.bindImage(imgUrl: String?) {
    imgUrl?.let {
        val imageIcon = "https://openweathermap.org/img/w/$it.png"
        Glide.with(this.context)
            .load(imageIcon)
            .apply(
                RequestOptions()
                    .error(R.drawable.ic_broken_image)
            )
            .into(this)

    }
}

