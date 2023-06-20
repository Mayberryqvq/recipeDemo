package com.mayberry.recipedemo.fragment.recipe.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.google.android.material.chip.Chip
import com.mayberry.recipedemo.R

object BindingAdapter {
    @JvmStatic
    @BindingAdapter("loadImageWithUrl")
    fun loadImageWithUrl(imageView: ImageView, url: String) {
        //将url对应的图片下载下来，显示到imageView上
        //Glide
        Glide.with(imageView).load(url).into(imageView)
    }

    @JvmStatic
    @BindingAdapter("loadIngredientImageWithName")
    fun loadIngredientImageWithName(imageView: ImageView, name: String) {
        //将url对应的图片下载下来，显示到imageView上
        //Glide
        val imageBaseUrl = "https://spoonacular.com/cdn/ingredients_250x250/"
        Glide.with(imageView).load(imageBaseUrl + name).placeholder(R.drawable.ic_launcher_background).into(imageView)
    }

    @JvmStatic
    @BindingAdapter("changeStatus")
    fun changeStatus(chip: Chip, status: Boolean) {
        chip.isSelected = status
    }

}