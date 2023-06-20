package com.mayberry.recipedemo.fragment.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mayberry.recipedemo.data.model.Result
import com.mayberry.recipedemo.databinding.FragmentFavoriteBinding
import com.mayberry.recipedemo.viewmodel.FavoriteViewModel

class FavoriteFragment : Fragment() {
    private lateinit var binding: FragmentFavoriteBinding
    private val favoriteAdapter = FavoriteAdapter()
    private val favoriteViewModel: FavoriteViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentFavoriteBinding.inflate(inflater)
        binding.favoriteRecyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        binding.favoriteRecyclerView.adapter = favoriteAdapter
        favoriteViewModel.readFavorites()
        favoriteViewModel.favoriteRecipes.observe(viewLifecycleOwner) {
            val resultList = mutableListOf<Result>()
            it.forEach { entity ->
                resultList.add(entity.recipe)
            }
            favoriteAdapter.setData(resultList)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.favoriteBackBtn.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

}