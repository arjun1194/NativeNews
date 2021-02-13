package com.arjun1194.nativenews.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide


fun ImageView.load(url: String){
    Glide.with(this.context).load(url).into(this)
}