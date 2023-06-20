package com.mayberry.recipedemo.fragment.recipe.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mayberry.recipedemo.data.model.ExtendedIngredient
import com.mayberry.recipedemo.databinding.FragmentIngredientBinding
import com.mayberry.recipedemo.fragment.recipe.adapter.IngredientAdapter

class IngredientFragment(private val ingredientList: List<ExtendedIngredient>) : Fragment() {
    private lateinit var binding: FragmentIngredientBinding
    private val ingredientAdapter = IngredientAdapter()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentIngredientBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.ingredientRecyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        binding.ingredientRecyclerView.adapter = ingredientAdapter
        ingredientAdapter.setData(ingredientList)
    }

}