package com.mayberry.recipedemo.fragment.recipe.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mayberry.recipedemo.R
import com.mayberry.recipedemo.databinding.FragmentSummaryBinding

class SummaryFragment(private val summary: String) : Fragment() {
    private lateinit var binding: FragmentSummaryBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSummaryBinding.inflate(layoutInflater)
        binding.summaryTextView.text = summary
        return binding.root
    }

}