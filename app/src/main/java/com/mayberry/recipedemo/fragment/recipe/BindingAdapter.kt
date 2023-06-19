package com.mayberry.recipedemo.fragment.recipe

import android.provider.ContactsContract.CommonDataKinds.Im
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.google.android.material.chip.Chip
import com.mayberry.recipedemo.R
import com.mayberry.recipedemo.data.model.Result

object BindingAdapter {
    @JvmStatic
    @BindingAdapter("loadImageWithUrl")
    fun loadImageWithUrl(imageView: ImageView, url: String) {
        //将url对应的图片下载下来，显示到imageView上
        //Glide
        Glide.with(imageView).load(url).into(imageView)
    }

    @JvmStatic
    @BindingAdapter("navigateToDetail")
    fun navigateToDetail(view: View, result: Result) {
        val header = view.findViewById<ImageView>(R.id.header)
        header.findNavController().navigate(R.id.action_recipeFragment_to_detailFragment)
    }

    @JvmStatic
    @BindingAdapter("changeStatus")
    fun changeStatus(chip: Chip, status: Boolean) {
        chip.isSelected = status
    }
}