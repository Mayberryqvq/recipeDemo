package com.mayberry.recipedemo.fragment.recipe.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.mayberry.recipedemo.R
import com.mayberry.recipedemo.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding
    private val recipeArgs: DetailFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(inflater)
        binding.detailsBtn.isSelected = true
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recipe = recipeArgs.recipe
        binding.executePendingBindings()
        binding.backBtn.setOnClickListener {
            requireActivity().onBackPressed()
        }
        binding.detailsBtn.setOnClickListener {
            if (!binding.detailsBtn.isSelected) {
                binding.detailsBtn.isSelected = true
                binding.ingredientsBtn.isSelected = false
                val distance = binding.detailsBtn.x - binding.ingredientsBtn.x
                indicatorAnim(0f)
            }
        }
        binding.ingredientsBtn.setOnClickListener {
            if (!binding.ingredientsBtn.isSelected) {
                binding.ingredientsBtn.isSelected = true
                binding.detailsBtn.isSelected = false
                val distance = binding.ingredientsBtn.x - binding.detailsBtn.x
                indicatorAnim(distance)
            }
        }
    }

    private fun indicatorAnim(value: Float) {
        binding.indicatorView.animate().translationX(value).setDuration(300).start()
    }

}