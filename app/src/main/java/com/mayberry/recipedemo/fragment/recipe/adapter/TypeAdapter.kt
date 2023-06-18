package com.mayberry.recipedemo.fragment.recipe.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mayberry.recipedemo.databinding.ItemTypeBinding

class TypeAdapter: RecyclerView.Adapter<TypeAdapter.MyViewHolder>() {
    //接受事件回调的lambda
    var callBack:((current: Int, last: Int) -> Unit)? = null
    val typeList = listOf("main course", "side dish", "dessert", "appetizer",
        "salad", "bread", "breakfast", "soup", "beverage", "source", "marinade",
        "fingerfood", "snack", "drink")
    private var lastSelectedPosition = 0

    class MyViewHolder(private val binding:ItemTypeBinding): RecyclerView.ViewHolder(binding.root) {
        //数据回调
        var callBack:((Int) -> Unit)? = null
        companion object {
            //创建ViewHolder
            fun from(parent: ViewGroup): MyViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                return MyViewHolder(ItemTypeBinding.inflate(inflater))
            }
        }

        //绑定数据
        fun bind(type: String, position: Int) {
            binding.titleTextView.text = type
            binding.titleTextView.setOnClickListener {
                callBack?.let { it(position) }
            }
        }

        fun changeSelectedStatus(status: Boolean) {
            binding.titleTextView.isSelected = status

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val holder = MyViewHolder.from(parent)
        holder.callBack = {
            //点的是不是同一个
            if (it != lastSelectedPosition) {
                callBack?.let { call ->
                    call(it, lastSelectedPosition)
                    //记录当前索引
                    lastSelectedPosition = it
                }
            }
        }
        return holder
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(typeList[position], position)
        if (position == lastSelectedPosition) {
            holder.changeSelectedStatus(true)
        } else {
            holder.changeSelectedStatus(false)
        }
    }

    override fun getItemCount(): Int {
        return typeList.size
    }
}