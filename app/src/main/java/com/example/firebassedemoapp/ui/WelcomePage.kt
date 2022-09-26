package com.example.firebassedemoapp.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.firebassedemoapp.R
import com.example.firebassedemoapp.databinding.FragmentWelcomePageBinding


class WelcomePage : Fragment() {
    private  var _binding : FragmentWelcomePageBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        // Inflate the layout for this fragment
        _binding = FragmentWelcomePageBinding.inflate(inflater, container, false)

        binding.login.setOnClickListener {
            //Navigation.findNavController(view).navigate(R.id.action_welcomePage_to_loginPage)
            //Navigation.findNavController(requireActivity(),R.id.welcomePage).navigate(R.id.action_welcomePage_to_loginPage)
            //it.findNavController().navigate(R.id.action_welcomePage_to_loginPage)

            it.findNavController().navigate(R.id.action_welcomePage_to_loginPage)








            println("helloooooo")
        }

        binding.register.setOnClickListener {
            //Navigation.findNavController(view).navigate(R.id.action_welcomePage_to_loginPage)
            //Navigation.findNavController(requireActivity(),R.id.welcomePage).navigate(R.id.action_welcomePage_to_loginPage)
            //it.findNavController().navigate(R.id.action_welcomePage_to_loginPage)

            it.findNavController().navigate(R.id.action_welcomePage_to_registerPage)
        }

        return binding.root
    }

}