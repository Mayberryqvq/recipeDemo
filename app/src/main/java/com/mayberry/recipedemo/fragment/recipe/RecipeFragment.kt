package com.mayberry.recipedemo.fragment.recipe

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mayberry.recipedemo.R
import com.mayberry.recipedemo.databinding.FragmentRecipeBinding
import com.mayberry.recipedemo.fragment.recipe.adapter.TypeAdapter

class RecipeFragment : Fragment() {

    private lateinit var binding: FragmentRecipeBinding
    private val typeAdapter = TypeAdapter()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRecipeBinding.inflate(inflater)
        initRecyclerView()
        return binding.root
    }

    private fun initRecyclerView() {
        //配置类型选择的recyclerView
        binding.typeRecyclerView.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        binding.typeRecyclerView.adapter = typeAdapter
    }
}