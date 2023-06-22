package com.mayberry.recipedemo.fragment.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mayberry.recipedemo.data.model.Result
import com.mayberry.recipedemo.databinding.FragmentFavoriteBinding
import com.mayberry.recipedemo.util.showToast
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
        swipeToDelete()
        binding.favoriteBackBtn.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    /** 滑动删除 **/
    private fun swipeToDelete() {
        val touchHelper = ItemTouchHelper(object: ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val index = viewHolder.adapterPosition
                val data = favoriteViewModel.favoriteRecipes.value!![index]
                favoriteViewModel.deleteFavorite(data)
                Toast.makeText(requireContext(), "Delete have finished!", Toast.LENGTH_LONG).show()
            }
        })
        touchHelper.attachToRecyclerView(binding.favoriteRecyclerView)
    }

}