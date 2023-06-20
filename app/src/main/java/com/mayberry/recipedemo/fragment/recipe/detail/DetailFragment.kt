package com.mayberry.recipedemo.fragment.recipe.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.ViewPager2
import com.mayberry.recipedemo.databinding.FragmentDetailBinding
import com.mayberry.recipedemo.fragment.recipe.adapter.ViewPagerAdapter

class DetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding
    private val recipeArgs: DetailFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater)
        binding.detailsBtn.isSelected = true
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recipe = recipeArgs.recipe
        binding.executePendingBindings()
        initEvent()
        initViewPager()
    }

    private fun indicatorAnim(value: Float) {
        binding.indicatorView.animate().translationX(value).setDuration(300).start()
    }

    private fun initEvent() {
        binding.backBtn.setOnClickListener {
            requireActivity().onBackPressed()
        }
        binding.detailsBtn.setOnClickListener {
            selectDetails()
            binding.viewPager.currentItem = 0
        }
        binding.ingredientsBtn.setOnClickListener {
            selectIngredients()
            binding.viewPager.currentItem = 1
        }
        binding.viewPager.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                if (position == 0) {
                    selectDetails()
                } else {
                    selectIngredients()
                }
            }
        })
    }

    private fun initViewPager() {
        val fragments = listOf(SummaryFragment(recipeArgs.recipe.summary), IngredientFragment(recipeArgs.recipe.extendedIngredients))
        binding.viewPager.adapter = ViewPagerAdapter(fragments, requireActivity().supportFragmentManager, lifecycle)
    }

    private fun selectDetails() {
        if (!binding.detailsBtn.isSelected) {
            binding.detailsBtn.isSelected = true
            binding.ingredientsBtn.isSelected = false
            indicatorAnim(0f)
        }
    }

    private fun selectIngredients() {
        if (!binding.ingredientsBtn.isSelected) {
            binding.ingredientsBtn.isSelected = true
            binding.detailsBtn.isSelected = false
            val distance = binding.ingredientsBtn.x - binding.detailsBtn.x
            indicatorAnim(distance)
        }
    }
}