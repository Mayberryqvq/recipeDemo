package com.mayberry.recipedemo.fragment.recipe.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mayberry.recipedemo.databinding.ItemTypeBinding

class TypeAdapter: RecyclerView.Adapter<TypeAdapter.MyViewHolder>() {
    private val typeList = listOf("main course", "side dish", "dessert", "appetizer",
        "salad", "bread", "breakfast", "soup", "beverage", "source", "marinade",
        "fingerfood", "snack", "drink")

    class MyViewHolder(private val binding:ItemTypeBinding): RecyclerView.ViewHolder(binding.root) {
        companion object {
            //创建ViewHolder
            fun from(parent: ViewGroup): MyViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                return MyViewHolder(ItemTypeBinding.inflate(inflater))
            }
        }

        //绑定数据
        fun bind(type: String) {
            binding.titleTextView.text = type
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        return holder.bind(typeList[position])
    }

    override fun getItemCount(): Int {
        return typeList.size
    }
}