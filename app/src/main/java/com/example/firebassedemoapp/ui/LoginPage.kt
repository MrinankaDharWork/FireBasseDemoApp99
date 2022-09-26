package com.example.firebassedemoapp.ui

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.firebassedemoapp.R
import com.example.firebassedemoapp.databinding.FragmentLoginPageBinding
import com.example.firebassedemoapp.databinding.FragmentWelcomePageBinding


class LoginPage : Fragment(R.layout.fragment_login_page) {

    private var _binding: FragmentLoginPageBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)




        binding.loginButton.setOnClickListener {
            val enteredEmail = binding.emailInput.text.toString()
            val enteredPassword = binding.passwordInput.text.toString()

            if (TextUtils.isEmpty((enteredEmail))){
                binding.emailInput.error = "This field can not be left empty"
            }
            if (TextUtils.isEmpty((enteredPassword))){
                binding.emailInput.error = "This field can not be left empty"
            }
            else{
                it.findNavController().navigate(R.id.action_loginPage_to_mainPage)
            }


        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}