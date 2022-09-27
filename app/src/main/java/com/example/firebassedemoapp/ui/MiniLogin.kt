package com.example.firebassedemoapp.ui

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import com.example.firebassedemoapp.R
import com.example.firebassedemoapp.databinding.FragmentMiniLoginBinding
import com.google.firebase.auth.FirebaseAuth


class MiniLogin : Fragment() {

    private var _binding : FragmentMiniLoginBinding? = null
    private val binding get() = _binding!!
    private lateinit var auth: FirebaseAuth
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMiniLoginBinding.inflate(inflater, container, false)
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

                loginUser(enteredEmail,enteredPassword)


            }

        }


        binding.signup.setOnClickListener {
            val navOptions: NavOptions = NavOptions.Builder()
                .setPopUpTo(R.id.miniLogin, true)
                .build()

            it.findNavController().navigate(R.id.action_miniLogin_to_miniRegister,null,navOptions)
        }

        binding.forgetPassword.setOnClickListener{
            val enteredEmail = binding.emailInput.text.toString()

            if ((TextUtils.isEmpty(enteredEmail)) || ("@" !in enteredEmail)) {
                binding.emailInput.error = "Enter a valid Email id"
            } else{
                //auth.sendPasswordResetEmail(enteredEmail).addOnCompleteListener {
                Toast.makeText(requireContext(),"Email sent successfully", Toast.LENGTH_LONG).show()
                //TODO("Implement reset password method")
                //}
            }


        }
    }

    private fun loginUser(enteredEmail: String, enteredPassword: String) {
        auth.signInWithEmailAndPassword(enteredEmail,enteredPassword).addOnCompleteListener {
            if (it.isSuccessful){
                val navOption : NavOptions = NavOptions.Builder()
                    .setPopUpTo(R.id.miniLogin ,true).build()

                navController.navigate(R.id.action_miniLogin_to_mainPage2,null,navOption)
                Toast.makeText(requireContext(),"Logged in Successfully", Toast.LENGTH_LONG).show()
            } else{
                Toast.makeText(requireContext(),"Incorrect Credentials", Toast.LENGTH_LONG).show()
            }

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }



}
