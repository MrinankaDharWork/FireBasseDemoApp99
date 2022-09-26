package com.example.firebassedemoapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.firebassedemoapp.R
import com.example.firebassedemoapp.databinding.FragmentLoginPageBinding
import com.example.firebassedemoapp.databinding.FragmentMainPageBinding
import com.google.firebase.auth.FirebaseAuth


class MainPage : Fragment() {
    private var _binding: FragmentMainPageBinding? = null
    private val binding get() = _binding!!
    //private lateinit var auth: FirebaseAuth
    //private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentMainPageBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.logout.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            Toast.makeText(requireContext(),"Logged Out Successfully", Toast.LENGTH_LONG).show()
            it.findNavController().navigate(R.id.action_mainPage_to_welcomePage)

        }

    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}