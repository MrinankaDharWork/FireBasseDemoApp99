package com.example.firebassedemoapp.ui

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import com.example.firebassedemoapp.R
import com.example.firebassedemoapp.databinding.FragmentLoginPageBinding
import com.example.firebassedemoapp.databinding.FragmentRegisterPageBinding
import com.google.firebase.auth.FirebaseAuth


class RegisterPage : Fragment() {


    private var _binding: FragmentRegisterPageBinding? = null
    private val binding get() = _binding!!
    private lateinit var auth: FirebaseAuth
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRegisterPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.registerButton.setOnClickListener {
            val enteredEmail = binding.emailInput.text.toString()
            val enteredPassword = binding.passwordInput.text.toString()
            var enteredValidEmailCheck = false
            var enteredValidPasswordCheck = false
            auth = FirebaseAuth.getInstance()
            navController = it.findNavController()


            if ((TextUtils.isEmpty(enteredEmail)) || ("@" !in enteredEmail)) {
                binding.emailInput.error = "Enter a valid Email id"
            } else{
                enteredValidEmailCheck = true
            }

            if ((TextUtils.isEmpty(enteredPassword)) || (enteredPassword.length<6) ){
                binding.passwordInput.error = "Enter a valid 6 Char password"
            } else{
                enteredValidPasswordCheck = true
            }


            if (enteredValidEmailCheck && enteredValidPasswordCheck){

                registerUser(enteredEmail,enteredPassword)
            }

        }
    }

    private fun registerUser(enteredEmail: String, enteredPassword: String) {
        auth.createUserWithEmailAndPassword(enteredEmail,enteredPassword).addOnCompleteListener {
            if (it.isSuccessful){
                navController.navigate(R.id.action_registerPage_to_mainPage)
                Toast.makeText(requireContext(),"Signed up Successfully",Toast.LENGTH_LONG).show()

            } else{
                Toast.makeText(requireContext(),"Unsuccessful operation",Toast.LENGTH_LONG).show()
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}