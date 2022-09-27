package com.example.firebassedemoapp.ui

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import com.example.firebassedemoapp.R
import com.example.firebassedemoapp.databinding.FragmentMiniLoginBinding
import com.example.firebassedemoapp.databinding.FragmentMiniRegisterBinding
import com.google.firebase.auth.FirebaseAuth


class MiniRegister : Fragment() {
    private var _binding : FragmentMiniRegisterBinding? = null
    private val binding get() = _binding!!
    private lateinit var auth: FirebaseAuth
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMiniRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.login.setOnClickListener {
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

                signupUser(enteredEmail,enteredPassword)


            }

        }
        binding.signin.setOnClickListener {
            val navOptions: NavOptions = NavOptions.Builder()
                .setPopUpTo(R.id.miniRegister, true)
                .build()
            it.findNavController().navigate(R.id.action_miniRegister_to_miniLogin,null,navOptions)
        }


    }

    private fun signupUser(enteredEmail: String, enteredPassword: String) {
        auth.createUserWithEmailAndPassword(enteredEmail,enteredPassword).addOnCompleteListener {
            if (it.isSuccessful){
                val navOptions: NavOptions = NavOptions.Builder()
                    .setPopUpTo(R.id.miniRegister, true)
                    .build()
                navController.navigate(R.id.action_miniRegister_to_mainPage2,null,navOptions)
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