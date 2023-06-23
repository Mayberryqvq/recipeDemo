package com.mayberry.recipedemo.fragment.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.mayberry.recipedemo.R
import com.mayberry.recipedemo.databinding.FragmentRegisterBinding

class RegisterFragment : Fragment() {
    private lateinit var binding: FragmentRegisterBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentRegisterBinding.inflate(inflater)
        binding.registerCreateBtn.setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment_to_recipeFragment)
        }
        return binding.root
    }

}